/*----- cart.html的頁面操作 -----*/
// tab區塊
function tab_active() {
	let target_tab;
	switch (location.hash) {
		case "#tab1":
			target_tab = "tab1";
			break;
		case "#tab2":
			target_tab = "tab2";
			break;
		case "#tab3":
			target_tab = "tab3";
			break;
		default:
			target_tab = "tab1";
	}

	$("a.tab").removeClass("-on");
	$("a.tab[data-target=" + target_tab + "]").addClass("-on");

	$("div.tab").removeClass("-on");
	$("div.tab." + target_tab).addClass("-on");
}

$(function() {
	// 連到外部網站，再按上一頁時，會觸發 DOMContentLoaded 事件，所以再執行
	tab_active();

	$("a.tab").on("click", function(e) {
		e.preventDefault();

		/* 將頁籤列表移除所有 -on，再將指定的加上 -on */
		$(this).closest("ul").find("a.tab").removeClass("-on");
		$(this).addClass("-on");

		/* 找到對應的頁籤內容，加上 -on 來顯示 */
		$("div.tab").removeClass("-on");
		$("div.tab." + $(this).attr("data-target")).addClass("-on");

		history.pushState(null, null, "#" + $(this).attr("data-target"));
	});

	$(".tab1_btn").on("click", function(e) {
		e.preventDefault();
		let cartItems = sessionStorage.getItem("productsInCart");
		if (cartItems !== null) {
			$(".tab").removeClass("-on");
			$("a.tab[data-target= tab2]").addClass("-on");
			$("div.tab2").addClass("-on");
			history.pushState(null, null, "#" + "tab2");
		} else {
			alert("請先選取租借商品");
			location = "./rentFront.html"
		}

	});
});

window.addEventListener("popstate", function() {
	tab_active();
});

// 卡號欄位
$(".cardNo").focus(function() {
	$(".cardNo").keyup(function(e) {
		if (e.which >= 48 && e.which <= 57 || e.which == 8) {
			return true;
		} else {
			$(this).val("")
		}
	});

	$("#cardNo_1").keyup(function() {
		if ($(this).val().length == 4)
			$("#cardNo_1").next().focus();
	})
	$("#cardNo_2").keyup(function() {
		if ($(this).val().length == 4)
			$("#cardNo_2").next().focus();
	})
	$("#cardNo_3").keyup(function() {
		if ($(this).val().length == 4)
			$("#cardNo_3").next().focus();
	})
});


$("#date").keyup(function(e) {

	if ($(this).val().length == 2) {
		$(this).val($(this).val() + "/")
	}
	if (e.which >= 48 && e.which <= 57 || e.which == 8) {
		return true;
	} else {
		$(this).val("")
	}
});

$("#cvv").keyup(function(e) {

	if (e.which >= 48 && e.which <= 57 || e.which == 8) {
		return true;
	} else {
		$(this).val("")
	}
});



/*------- 購物相關操作 -------*/

//購物車 icon 數字
function onLoadCartNumbers() {
	let productNumbers = sessionStorage.getItem("cartNumbers");
	if (productNumbers) {
		document.querySelector("#cart span").textContent = productNumbers;

	}
}

// 購物車數字、總數+-
function cartNumbers(product, action) {
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
		} else {
			if (cartItems[product.name].inCart < cartItems[product.name].count) {
				sessionStorage.setItem("cartNumbers", productNumbers + 1);
				document.querySelector("#cart span").textContent = productNumbers + 1;
			}
		}
	} else {
		sessionStorage.setItem("cartNumbers", 1);
		document.querySelector("#cart span").textContent = 1;
	}
	setItem(product);
}

// 不同產品的資料跟數量
function setItem(product) {
	let cartItems = sessionStorage.getItem("productsInCart");
	cartItems = JSON.parse(cartItems);

	if (cartItems != null) {

		if (cartItems[product.name] == undefined) {  //不同產品
			cartItems = {
				...cartItems,  //rest operator from JS
				[product.name]: product
			}
		}
		if (cartItems[product.name].inCart < cartItems[product.name].count) {
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

// 選擇場館
$("#gymName").change(function() {
	let gymName = $("#gymName option:selected").text();
	sessionStorage.setItem("gymName", gymName);
	sessionStorage.setItem("gymId", $("#gymName").val());
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
	let rentList = document.querySelector(".prdList");
	let gymName = sessionStorage.getItem("gymName");
	let prdInfo = document.querySelector(".prdInfo");

	if (cartItems && productContainer) {
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
                        <span class="msg"> </span>
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
	let cartCost = sessionStorage.getItem("totalCost");

	for (let i = 0; i < deleteButtons.length; i++) {
		deleteButtons[i].addEventListener("click", () => {
			productName = deleteButtons[i].closest(".product").children[1].textContent;
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
	let msgs = document.querySelectorAll(".msg");
	let cartItems = sessionStorage.getItem("productsInCart");
	//	let currentQuantity = 0;
	let currentProduct = "";
	cartItems = JSON.parse(cartItems);

	for (let i = 0; i < minusButtons.length; i++) {
		minusButtons[i].addEventListener("click", () => {
			currentQuantity = minusButtons[i].parentElement.querySelector("span").textContent;
			currentProduct = minusButtons[i].closest(".product").children[1].textContent;

			if (cartItems[currentProduct].inCart > 1) {
				cartItems[currentProduct].inCart -= 1;
				cartNumbers(cartItems[currentProduct], "minus");
				sessionStorage.setItem("productsInCart", JSON.stringify(cartItems));
				displayCart();
			}
		});
	}

	for (let i = 0; i < plusButtons.length; i++) {
		plusButtons[i].addEventListener("click", () => {
			currentQuantity = plusButtons[i].parentElement.querySelector("span").textContent;
			currentProduct = plusButtons[i].closest(".product").children[1].textContent;

			if (cartItems[currentProduct].inCart < cartItems[currentProduct].count) {
				cartItems[currentProduct].inCart += 1;
				cartNumbers(cartItems[currentProduct]);
				sessionStorage.setItem("productsInCart", JSON.stringify(cartItems));
				displayCart();
			}else{
				msgs[i].innerText="數量上限"
			}
		})
	}
}


onLoadCartNumbers();
displayCart();



/*------- 訂單成立 -------*/

$(".tab2_btn").on("click", function() {
	let gymId = sessionStorage.getItem("gymId");
	let cartItems = sessionStorage.getItem("productsInCart");
	cartItems = JSON.parse(cartItems);
//	console.log(Object.values(cartItems).map(item => item.id))
//	console.log(cartItems);
	var a = Object.keys(cartItems);
	var prodsInCart = [];
	var obj = {};
	for (let i = 0; i < a.length; i++) {
		obj.prodId = cartItems[a[i]].id;
		obj.inCart = cartItems[a[i]].inCart;
		obj.gymId = gymId;
		prodsInCart.push({ ...obj });
	}
	
	if (sessionStorage.getItem("payfor") !== null) {

		fetch('http://localhost:8080/simplefitness-servlet/member/checkout', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(prodsInCart),
		})
			.then(resp => resp.json())
			.then(body => {
				if (body.msg !== "success") {
					alert("租借數量大於庫存，請重新選取");
					sessionStorage.removeItem("productsInCart");
					sessionStorage.removeItem("cartNumbers");
					location = "./rentFront.html";
				} else {
					let amount = sessionStorage.getItem("totalCost");
					let gymId = sessionStorage.getItem("gymId");
					fetch('http://localhost:8080/simplefitness-servlet/order/addOrder', {
						method: 'POST',
						headers: { 'Content-Type': 'application/json' },
						body: JSON.stringify({
							
							gymId: gymId,
							amount: amount,
							orderList:prodsInCart
						}),
					})
						.then(resp => resp.json())
						.then(body => {
							sessionStorage.clear();
							document.querySelector("#cart span").textContent = 0
							$(".tab").removeClass("-on");
							$("a.tab[data-target= tab3]").addClass("-on");
							$("div.tab3").addClass("-on");
							history.pushState(null, null, "#" + "tab3");
							$(".startTime").text(body.orderDate.replace("T"," "));
							$(".memName").text(body.memName);
						});
				}

			});

	} else {
		alert("請選擇付款方式");
	}

});