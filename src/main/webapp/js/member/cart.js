
//購物車 icon 數字
function onLoadCartNumbers() {
	let productNumbers = sessionStorage.getItem("cartNumbers");
	if (productNumbers) {
		document.querySelector("#cart span").textContent = productNumbers;

	}
}


// 購物車數字、總數+-
function cartNumbers(product, action) {
	// console.log(product);
	let productNumbers = sessionStorage.getItem("cartNumbers");
	let cartItems = sessionStorage.getItem("productsInCart");
	cartItems = JSON.parse(cartItems);
	productNumbers = parseInt(productNumbers);

	if (action == "minus") {
		sessionStorage.setItem("cartNumbers", productNumbers - 1);
		document.querySelector('#cart span').textContent = productNumbers - 1;
	} else if (productNumbers) {

		if (cartItems[product.name] == undefined) {
			sessionStorage.setItem("cartNumbers", productNumbers + 1);
			document.querySelector("#cart span").textContent = productNumbers + 1;
		}else{
			if(cartItems[product.name].inCart < cartItems[product.name].count){
			sessionStorage.setItem("cartNumbers", productNumbers + 1);
			document.querySelector("#cart span").textContent = productNumbers + 1;
			}
		}
	} else {
		sessionStorage.setItem("cartNumbers", 1);
		document.querySelector("#cart span").textContent = 1;
		console.log(456);
	}
	setItem(product);
}

// 不同產品的資料跟數量
function setItem(product) {
	// console.log("my product is",product);
	let cartItems = sessionStorage.getItem("productsInCart");
	cartItems = JSON.parse(cartItems);

	if (cartItems != null) {
		// console.log(cartItems[product.tag]); //undefined

		if (cartItems[product.name] == undefined) {  //不同產品
			cartItems = {
				...cartItems,  //rest operator from JS
				[product.name]: product
			}
		}
		if(cartItems[product.name].inCart < cartItems[product.name].count){
			cartItems[product.name].inCart += 1;
		}
		
	} else {
		product.inCart = 1;
		cartItems = {
			[product.name]: product
		}
	}

	sessionStorage.setItem("productsInCart", JSON.stringify(cartItems));
}

// 總金額
//function totalCost(product, action) {
//	// console.log(product.price);
//	let cartCost = sessionStorage.getItem("totalCost");
//	// console.log(typeof cartCost); //string
//
//	if (action == "minus") {
//		cartCost = parseInt(cartCost);
//		sessionStorage.setItem("totalCost", cartCost - product.price);
//	} else if (cartCost != null) {
//		cartCost = parseInt(cartCost);
//		sessionStorage.setItem("totalCost", cartCost + product.price);
//	} else {
//		sessionStorage.setItem("totalCost", product.price);
//	}
//
//}

// 選擇場館
$("#gymName").change(function() {
	let gymName = $("#gymName option:selected").text();
	sessionStorage.setItem("gymName", gymName);
});

// 付款方式
$(".cash").on("click", function() {
   sessionStorage.setItem("payfor", "臨櫃支付");
});
$(".card").on("click", function() {
    sessionStorage.setItem("payfor", "信用卡支付");
});
$(".tab2_btn").on("click", function() {
    $(".lastPay").text(sessionStorage.getItem("payfor"));  
});

// 新增至購物車頁面
function displayCart() {
	let cartItems = sessionStorage.getItem("productsInCart");
	cartItems = JSON.parse(cartItems);

	let productContainer = document.querySelector(".item_body");
//	let cartCost = sessionStorage.getItem("totalCost");
	let rentList = document.querySelector(".prdList");
	let gymName = sessionStorage.getItem("gymName");
	let prdInfo = document.querySelector(".prdInfo");
	// console.log(gymName);

	// console.log(cartItems);
	if (cartItems && productContainer) {
		// console.log("runnig");
		productContainer.innerHTML = "";
		rentList.innerHTML = "";
		prdInfo.innerHTML = "";
		let totalCount = 0;
		let totalCost = 0;
		Object.values(cartItems).map(item => {
			productContainer.innerHTML += `

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
			
			totalCount += parseInt(item.inCart);
			totalCost += parseInt(item.inCart * item.price);
			
			// step3
			rentList.innerHTML += `
            <li><span class="prdName">${item.name}</span>：<span class="count">${item.inCart}</span></li>
            `
		});
		document.querySelector("#totalCount").innerText = `${totalCount}`;
		document.querySelector("#totalCost").innerText = `${totalCost}`;
		document.querySelector(".lastGym").innerText = `${gymName}`;
		sessionStorage.setItem("totalCost", totalCost);
		sessionStorage.setItem("cartNumbers", totalCount);
		
		 
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

	for (let i = 0; i < deleteButtons.length; i++) {
		deleteButtons[i].addEventListener("click", () => {
			console.log("clicked");
			productName = deleteButtons[i].closest(".product").children[1].textContent;
			console.log(productName);
			sessionStorage.setItem("cartNumbers", productNumbers - cartItems[productName].inCart);

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

	for (let i = 0; i < minusButtons.length; i++) {
		minusButtons[i].addEventListener("click", () => {
			console.log("minus");
			currentQuantity = minusButtons[i].parentElement.querySelector("span").textContent;
			console.log(currentQuantity);
			currentProduct = minusButtons[i].closest(".product").children[1].textContent;
			console.log(currentProduct);

			if (cartItems[currentProduct].inCart > 1) {
				cartItems[currentProduct].inCart -= 1;
				cartNumbers(cartItems[currentProduct], "minus");
//				totalCost(cartItems[currentProduct], "minus");
				sessionStorage.setItem("productsInCart", JSON.stringify(cartItems));
				displayCart();
			}
		});
	}

	for (let i = 0; i < plusButtons.length; i++) {
		plusButtons[i].addEventListener("click", () => {
			//            console.log("plus");
			currentQuantity = plusButtons[i].parentElement.querySelector("span").textContent;
			console.log(currentQuantity);

			currentProduct = plusButtons[i].closest(".product").children[1].textContent;
			//            console.log(currentProduct);

			if (cartItems[currentProduct].inCart < cartItems[currentProduct].count) {
				cartItems[currentProduct].inCart += 1;
				cartNumbers(cartItems[currentProduct]);
//				totalCost(cartItems[currentProduct]);
				sessionStorage.setItem("productsInCart", JSON.stringify(cartItems));
				displayCart();
			}

		})
	}
}


onLoadCartNumbers();
displayCart();
