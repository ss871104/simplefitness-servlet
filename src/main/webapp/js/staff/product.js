(() => {
	// 一進來get all
	fetch("/simplefitness-servlet/product/getAllProduct")
		.then(resp => resp.json())
		.then(product => {
			for (i = 0; i < product["length"]; i++) {
				let text = `
		  	<tr>
				<td>${product[i].prodId}</td>
				<td>${product[i].prodName}</td>
				<td>${product[i].price}</td>
				<td><button type="button" class="btn btn-secondary" id="idv${product[i].prodId}" value="${product[i].prodId}">進入</button></td>
				<td><button type="button" class="btn btn-secondary" id="product${product[i].prodId}" value="${product[i].prodId}">進入</button></td>
	  		</tr>`;
				// 反引號 錢字號 大括號 三個為一包 出現在html 中的js
				$(".info").append(text);
			}

			// 點擊進入編輯
			for (i = 0; i < product["length"]; i++) {
				const button = document.querySelector(`#product${product[i].prodId}`);

				button.addEventListener('click', () => {
					sessionStorage.setItem('product', button.value);
					location = './product_edit.html';
				});
			}

			// 點擊進入個別產品
			for (i = 0; i < product["length"]; i++) {
				const button = document.querySelector(`#idv${product[i].prodId}`);
				// js基本語法, id與class, 抓id要用#來抓
				button.addEventListener('click', () => {
					sessionStorage.setItem('product', button.value);
					location = './idvproduct.html';
				});
			}
		});

	// 彈窗選擇圖片的預覽圖片
	window.addEventListener("load", function() {
		var the_file_element = document.getElementById("pic");
		the_file_element.addEventListener("change", function(e) {

			var preview_pic = document.getElementById("preview-pic");

			preview_pic.innerHTML = ""; // 讓預覽圖消失

			// 跑使用者選的檔案
			let reader = new FileReader(); // 用來讀取檔案
			reader.readAsDataURL(this.files[0]); // 讀取檔案
			reader.addEventListener("load", function() {
				console.log(reader.result);
				let pic_html = `
			<img src="${reader.result}" id="new-pic">
		  `;
				preview_pic.insertAdjacentHTML("beforeend", pic_html); // 加進節點
			});

		});

	});


	// 點擊新增
	const Pname = document.querySelector('#name-input');
	const price = document.querySelector('#price-input');
	const intro = document.querySelector('#intro-input');
	const preview_pic = document.querySelector('#preview-pic');
	const errMsg = document.querySelector('#errMsg');

	document.getElementById('add').addEventListener('click', () => {
		let picBase64 = document.querySelector('#new-pic');
		if (preview_pic.innerHTML == "") {
			picBase64 = "";
		} else {
			picBase64 = picBase64.src;
		}
		console.log(picBase64);

		fetch('/simplefitness-servlet/product/addItem', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				prodName: Pname.value,
				price: price.value,
				intro: intro.value,
				prodPicBase64: picBase64
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = './product.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});

})();

