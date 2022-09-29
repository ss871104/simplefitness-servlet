const gymName = document.querySelector("#gymName");

$("#gymName").change(function() {
	$(".item_list").html("");
	console.log(gymName.value);
	fetch("http://localhost:8080/simplefitness-servlet/product/selectByGym", {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			gymId: gymName.value
		}),
	})
		.then(resp => resp.json())
		.then(products => {


			for (i = 0; i < products["length"]; i++) {

				let html = `
                    <li>
                        <a href="./rentOne.html" class="idvPage">
                            <div class="img_block">
                                <img src="${products[i].prodPicBase64}"/>
                            </div>
                            
                            <span class="item_name">${products[i].prodName}</span>
                            <span class="price">$${products[i].price}</span>

                        </a>
                        <div class="cart-plus" title="加入購物車">
                            <a href="#"><i class="bi bi-cart-plus"></i></a>
                        </div>
                    </li>`;

				$(".item_list").append(html);
			}

			var cartproducts = [];
			var obj = {};
			for (let i = 0; i < products["length"]; i++) {
				obj.name = products[i].prodName;
				obj.price = products[i].price;
				obj.img = products[i].prodPicBase64;
				obj.intro = products[i].intro;
				obj.count = products[i].count;
				obj.inCart = 0;
				cartproducts.push({ ...obj });
			}

			let carts = document.querySelectorAll(".cart-plus");
			let pages = document.querySelectorAll(".idvPage");
			for (let i = 0; i < carts.length; i++) {
				carts[i].addEventListener("click", (e) => {
					e.preventDefault();
					cartNumbers(cartproducts[i]);
				})
				
				pages[i].addEventListener("click", () => {
					sessionStorage.setItem("cartproduct", JSON.stringify(cartproducts[i]));
				})
			}

		});
});