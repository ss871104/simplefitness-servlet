(() => {
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	});
	
	// selectById
	var prodId = sessionStorage.getItem('product');
	prodId = parseInt(prodId);
	console.log(prodId);
	fetch("http://localhost:8080/simplefitness-servlet/product/SelectById", {
	method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				prodId: prodId,
			}),
		})
			.then(resp => resp.json())
			.then(product => {
				if (product.prodPicBase64 != null) {
					document.querySelector('.profile-pic').innerHTML = `
					<img src="${product.prodPicBase64}">
				`;
				} else {
					document.querySelector('.profile-pic').innerHTML = `
					<img src=""></img>
					`;
				}
				// . 是class # 是id
				document.querySelector('#name').textContent = product.prodName;
				document.querySelector('#price').textContent = product.price;
				document.querySelector('#intro').textContent = product.intro;

				document.querySelector('#name-input').value = product.prodName;
				document.querySelector('#price-input').value = product.price;
				document.querySelector('#intro-input').value = product.intro;
				
			})

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

	// 編輯資料
	const Pname = document.querySelector('#name-input');
	const price = document.querySelector('#price-input');
	const intro = document.querySelector('#intro-input');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('edit').addEventListener('click', () => {
		let picBase64 = document.querySelector('#new-pic');
		const preview_pic = document.querySelector('#preview-pic');
		if (preview_pic.innerHTML == "") {
			picBase64 = "";
		} else {
			picBase64 = picBase64.src;
		}
		fetch('http://localhost:8080/simplefitness-servlet/product/edit', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				// memPicBase64: picBase64,
				prodName: Pname.value,
				price: price.value,
				intro: intro.value,
				prodId: prodId
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = './product_edit.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});

	document.getElementById('logout').addEventListener('click', () => {
		fetch("http://localhost:8080/simplefitness-servlet/member/logout")
			.then(body => {
				location = '../guest/home.html';
			});
	});
})();