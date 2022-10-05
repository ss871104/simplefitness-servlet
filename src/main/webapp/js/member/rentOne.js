cartproduct = sessionStorage.getItem("cartproduct");
cartproduct = JSON.parse(cartproduct);
$(".img_block").html(`<img id="prdImg" src="${cartproduct.img}"/>`);
$(".prdName").text(cartproduct.name);
$(".prdInfo").html(`<li>${cartproduct.intro}</li>`);
$(".prdPrice").text(cartproduct.price);
for (i = 1; i <= cartproduct.count; i++) {
	let html = `<option value="${i}">${i}</option>`;
	$("#count").append(html);
};

$(".cart-plus").on("click", function(e) {
	e.preventDefault();
	cartNumbers(cartproduct);
})
$(".cart-check").on("click", function() {
	cartNumbers(cartproduct);
	location = "./cart.html";
})

function cartNumbers(product) {
	let productNumbers = sessionStorage.getItem("cartNumbers");
	let cartItems = sessionStorage.getItem("productsInCart");
	cartItems = JSON.parse(cartItems);
	productNumbers = parseInt(productNumbers);
	let prdCount = parseInt($("#count").val());
	if (productNumbers) {
		if (cartItems[product.name] == undefined) {
			sessionStorage.setItem("cartNumbers", productNumbers + prdCount);
			document.querySelector("#cart span").textContent = productNumbers + prdCount;
		} else {
			if (cartItems[product.name].inCart < cartItems[product.name].count) {
				sessionStorage.setItem("cartNumbers", productNumbers + prdCount);
				document.querySelector("#cart span").textContent = productNumbers + prdCount;
			}
		}
	} else {
		sessionStorage.setItem("cartNumbers", prdCount);
		document.querySelector("#cart span").textContent = prdCount;
	}
	setItem(product);
}

function setItem(product) {
	let cartItems = sessionStorage.getItem("productsInCart");
	cartItems = JSON.parse(cartItems);
	let prdCount = parseInt($("#count").val());

	if (cartItems != null) {

		if (cartItems[product.name] == undefined) {
			cartItems = {
				...cartItems,
				[product.name]: product
			}
		}
		if (cartItems[product.name].inCart < cartItems[product.name].count) {
			cartItems[product.name].inCart += prdCount;
		}

	} else {
		product.inCart = prdCount;
		cartItems = {
			[product.name]: product
		}
	}

	sessionStorage.setItem("productsInCart", JSON.stringify(cartItems));
}