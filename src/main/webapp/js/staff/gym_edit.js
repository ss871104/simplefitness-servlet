(() => {
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	});
	
	// GET session 出現資料庫資料
	var gymId = sessionStorage.getItem('gym');
	gymId = parseInt(gymId);
	console.log(gymId);
	fetch("http://localhost:8080/simplefitness-servlet/gym/getGymById", {
	method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				gymId: gymId,
			}),
		})
			.then(resp => resp.json())
			.then(gym => {
				// if (member.memPicBase64 != null) {
				// 	document.querySelector('#profile-pic').innerHTML = `
				// 	<img src="${member.memPicBase64}">
				// `;
				// } else {
				// 	document.querySelector('#profile-pic').innerHTML = `
				// 	<img src="../../img/no-profile.jpeg"></img>
				// 	`;
				// }
				// console.log(document.querySelector('#profile-pic').innerHTML);
				document.querySelector('#name').textContent = gym.gymName;
				document.querySelector('#address').textContent = gym.address;
				document.querySelector('#phone').textContent = gym.phone;
				document.querySelector('#publish').textContent = gym.openDate;
				document.querySelector('#open').textContent = gym.openTime;
				document.querySelector('#close').textContent = gym.closeTime;
				document.querySelector('#count').textContent = gym.maxPeople;
				document.querySelector('#intro').textContent = gym.intro;

				document.querySelector('#name-input').value = gym.gymName;
				document.querySelector('#address-input').value = gym.address;
				document.querySelector('#phone-input').value = gym.phone;
				document.querySelector('#publish-input').value = gym.openDate;
				document.querySelector('#open-input').value = gym.openTime;
				document.querySelector('#close-input').value = gym.closeTime;
				document.querySelector('#count-input').value = gym.maxPeople;
				document.querySelector('#intro-input').value = gym.intro;
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
	const Gname = document.querySelector('#name-input');
	const address = document.querySelector('#address-input');
	const phone = document.querySelector('#phone-input');
	const publish = document.querySelector('#publish-input');
	const open = document.querySelector('#open-input');
	const close = document.querySelector('#close-input');
	const count = document.querySelector('#count-input');
	const intro = document.querySelector('#intro-input');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('edit').addEventListener('click', () => {
		// let picBase64 = document.querySelector('#new-pic');
		// const preview_pic = document.querySelector('#preview-pic');
		// if (preview_pic.innerHTML == "") {
		// 	picBase64 = "";
		// } else {
		// 	picBase64 = picBase64.src;
		// }
		fetch('http://localhost:8080/simplefitness-servlet/gym/edit', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				// memPicBase64: picBase64,
				gymName: Gname.value,
				address: address.value,
				phone: phone.value,
				openDate: publish.value,
				openTime: open.value,
				closeTime: close.value,
				maxPeople: count.value,
				intro: intro.value,
				gymId: gymId
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = './gym_edit.html';
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