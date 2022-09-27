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
                        <a href="./rentGlove.html">
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
			
			
			let carts = document.querySelectorAll(".cart-plus");
			for (let i = 0; i < carts.length; i++) {
				carts[i].addEventListener("click", (e) => {
					console.log("click")
					e.preventDefault();
					cartNumbers(cartproducts[i]);
					totalCost(cartproducts[i]);
				})

			}


		})



});