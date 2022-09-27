//let carts= document.querySelectorAll(".cart-plus");
//console.log("load")
//let cartproducts = [
//    {
//        name: "拳擊手套",
//        tag: "glove",
//        price: 50,
//        inCart: 0
//    },
//    {
//        name: "拳擊手靶",
//        tag:"pad",
//        price: 50,
//        inCart: 0
//    },
//    {
//        name: "瑜珈墊",
//        tag: "yogaMat",
//        price: 20,
//        inCart: 0
//    },
//    {
//        name: "運動毛巾",
//        tag: "towel",
//        price: 20,
//        inCart: 0
//    },
//    {
//        name: "健身護腕",
//        tag: "wristWrap",
//        price: 50,
//        inCart: 0
//    },
//    {
//        name: "健身腰帶",
//        tag: "belt",
//        price: 50,
//        inCart: 0
//    },
//    {
//        name: "運動手錶",
//        tag: "watch",
//        price: 50,
//        inCart: 0
//    },
//    {
//        name: "瑜珈磚",
//        tag: "brick",
//        price: 50,
//        inCart: 0
//    },
//    {
//        name: "筋膜槍",
//        tag: "gun",
//        price: 100,
//        inCart: 0
//    },
//    {
//        name: "拉力帶",
//        tag: "band",
//        price: 50,
//        inCart: 0
//    }
//]



//for (let i=0; i< carts.length;i++){
//    carts[i].addEventListener("click",(e) =>{
//        console.log("added")
//        e.preventDefault();
//        cartNumbers(cartproducts[i]);
//        totalCost(cartproducts[i]);   
//    })
//
//}

//購物車 icon 數字
function onLoadCartNumbers(){
    let productNumbers = sessionStorage.getItem("cartNumbers");
    if(productNumbers){
        document.querySelector("#cart span").textContent = productNumbers;

    }
}


// 購物車數字、總數+-
function cartNumbers(product, action){
    // console.log(product);
    let productNumbers = sessionStorage.getItem("cartNumbers");
    // console.log(productNumbers);
    // console.log(typeof productNumbers);
    productNumbers = parseInt(productNumbers);
    // console.log(typeof productNumbers);

    if( action == "minus") {
        sessionStorage.setItem("cartNumbers", productNumbers -1);
        document.querySelector('#cart span').textContent = productNumbers -1;
    } else if( productNumbers ) {
        sessionStorage.setItem("cartNumbers", productNumbers +1);
        document.querySelector("#cart span").textContent = productNumbers +1;
    }else{
        sessionStorage.setItem("cartNumbers", 1);
        document.querySelector("#cart span").textContent =1;
    }
    setItem(product);
}

// 不同產品的資料跟數量
function setItem(product){
    // console.log("my product is",product);
    let cartItems = sessionStorage.getItem("productsInCart");
    cartItems = JSON.parse(cartItems);

    if(cartItems!= null){
        // console.log(cartItems[product.tag]); //undefined

        if(cartItems[product.name] == undefined){  //不同產品
            cartItems ={
                ...cartItems,  //rest operator from JS
                [product.name]:product
            }
        }
        cartItems[product.name].inCart +=1 ;
        
    }else{
        product.inCart =1;
        cartItems ={
            [product.name]:product
        }
    }
    
    sessionStorage.setItem("productsInCart", JSON.stringify(cartItems));
}

// 總金額
function totalCost(product, action){
    // console.log(product.price);
    let cartCost = sessionStorage.getItem("totalCost");
    // console.log(typeof cartCost); //string

    if( action == "minus") {
        cartCost = parseInt(cartCost);
        sessionStorage.setItem("totalCost", cartCost - product.price);
    } else if(cartCost != null) {
        cartCost = parseInt(cartCost);
        sessionStorage.setItem("totalCost", cartCost+ product.price);
    } else {
        sessionStorage.setItem("totalCost", product.price);
    }

}

$("#gymName").change(function(){
    let gymName = $("#gymName option:selected").text(); 
    sessionStorage.setItem("gymName", gymName);
    console.log(gymName);
})
// 新增至購物車頁面
function displayCart(){
    let cartItems = sessionStorage.getItem("productsInCart");
    cartItems = JSON.parse(cartItems);

    let productContainer = document.querySelector(".item_body");
    let productNumbers = sessionStorage.getItem("cartNumbers");
    let cartCost = sessionStorage.getItem("totalCost");
    let rentList = document.querySelector(".prdList");
    let gymName = sessionStorage.getItem("gymName");
    // console.log(gymName);
    
    // console.log(cartItems);
    if( cartItems && productContainer){
        // console.log("runnig");
        productContainer.innerHTML = "";
        rentList.innerHTML = "";
        Object.values(cartItems).map(item =>{
            productContainer.innerHTML +=`

                <div class="product">
                    <div class="prdImg">
                      <img id="prdImg" src="${item.img}" alt="">
                    </div>    
                    <div class="prdName">${item.name}</div>
                    <div class="gymName">${gymName}</div>
                    <div class="price">$<span class="prdPrice">${item.price}</span></div>
                    <div class="count">
                        <i class="bi bi-dash-square" id="minus"></i>
                        <span id="count"> ${item.inCart} </span>
                        <i class="bi bi-plus-square" id="plus"></i>    
                    </div> 
                    <div class="amount">$${item.inCart * item.price}</div>
                    <div class="operate">
                        <button>刪除</button>
                    </div>
                </div>
                `
                
			// step3
            rentList.innerHTML +=`
            <li><span class="prdName">${item.name}</span>：<span class="count">${item.inCart}</span></li>
            `
            
        });
        document.querySelector("#totalCount").innerText = `${productNumbers}`;
        document.querySelector("#totalCost").innerText = `${cartCost}`;
        document.querySelector(".lastGym").innerText = `${gymName}`;

    }
    deleteButtons();
    manageQuantity();
}

// 刪除 product
function deleteButtons() {
    let deleteButtons = document.querySelectorAll(".operate button");
    let productName;
    let productNumbers = sessionStorage.getItem("cartNumbers");
    let cartItems = sessionStorage.getItem("productsInCart");
    cartItems = JSON.parse(cartItems);
    // console.log(cartItems);
    let cartCost = sessionStorage.getItem("totalCost");

    for(let i=0; i < deleteButtons.length; i++) {
        deleteButtons[i].addEventListener("click", () => {
            console.log("clicked");
            productName = deleteButtons[i].closest(".product").children[1].textContent;
            console.log(productName);
            sessionStorage.setItem("cartNumbers", productNumbers - cartItems[productName].inCart );

            sessionStorage.setItem("totalCost", cartCost - (cartItems[productName].price * cartItems[productName].inCart));


            delete cartItems[productName];
            sessionStorage.setItem("productsInCart", JSON.stringify(cartItems));

            displayCart();
            onLoadCartNumbers();
        });
    }
}

// 數量+-
function manageQuantity() {
    let minusButtons = document.querySelectorAll("#minus");
    let plusButtons = document.querySelectorAll("#plus");
    let cartItems = sessionStorage.getItem("productsInCart");
    let currentQuantity = 0;
    let currentProduct = "";
    cartItems = JSON.parse(cartItems);
    // console.log(cartItems);

    for(let i=0; i < minusButtons.length; i++) {
        minusButtons[i].addEventListener("click", () => {
            console.log("minus");
            currentQuantity = minusButtons[i].parentElement.querySelector("span").textContent;
            console.log(currentQuantity);
            currentProduct = minusButtons[i].closest(".product").children[1].textContent;
            console.log(currentProduct);

            if( cartItems[currentProduct].inCart > 1 ) {
                cartItems[currentProduct].inCart -= 1;
                cartNumbers( cartItems[currentProduct], "minus" );
                totalCost( cartItems[currentProduct], "minus" );
                sessionStorage.setItem("productsInCart", JSON.stringify(cartItems));
                displayCart();
            }
        });
    }

    for(let i=0; i < plusButtons.length; i++) {
        plusButtons[i].addEventListener("click", () => {
            console.log("plus");
            currentQuantity = plusButtons[i].parentElement.querySelector("span").textContent;
            console.log(currentQuantity);

            currentProduct = plusButtons[i].closest(".product").children[1].textContent;
            console.log(currentProduct);
            
                cartItems[currentProduct].inCart += 1;
                cartNumbers(cartItems[currentProduct]);
                totalCost(cartItems[currentProduct]);
                sessionStorage.setItem("productsInCart", JSON.stringify(cartItems));
                displayCart();
            
        })
    }
}


onLoadCartNumbers();
displayCart();
