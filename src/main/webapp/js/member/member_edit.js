(() => {
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	});
	
	// GET session 出現資料庫資料
	fetch("http://localhost:8080/simplefitness-servlet/session")
		.then(resp => resp.json())
		.then(member => {
			if (member.memPicBase64 != null) {
				document.querySelector('#profile-pic').innerHTML = `
                <img src="${member.memPicBase64}">
              `;
			} else {
				document.querySelector('#profile-pic').innerHTML = `
				<img src="../../img/no-profile.jpeg"></img>
				`;
			}
			console.log(document.querySelector('#profile-pic').innerHTML);
			document.querySelector('#name').textContent = member.memName;
			document.querySelector('#nickname').textContent = member.memNickname;
			document.querySelector('#username').textContent = member.memUsername;
			document.querySelector('#email').textContent = member.memEmail;
			document.querySelector('#phone').textContent = member.memPhone;
			if (member.memGender == "0") {
				document.querySelector('#gender').textContent = "男";
			} else if (member.memGender == "1") {
				document.querySelector('#gender').textContent = "女";
			} else {
				document.querySelector('#gender').textContent = "";
			}
			document.querySelector('#birth').textContent = member.memBirth;
			document.querySelector('#register').textContent = member.memRegister;
			document.querySelector('#start').textContent = member.memStart;
			document.querySelector('#expire').textContent = member.memExpire;
			if (member.memLogin == null) {
				document.querySelector('#lastlogin').textContent = "第一次登入";
			} else {
				document.querySelector('#lastlogin').textContent = member.memLogin.replace('T', ' ');
			}
			if (member.memStatus == "0") {
				document.querySelector('#status').textContent = "無會籍";
			} else if (member.memStatus == "1") {
				document.querySelector('#status').textContent = "有會籍";
			} else {
				document.querySelector('#status').textContent = "";
			}
			document.getElementById("name-input").value = member.memName;
			document.getElementById("nickname-input").value = member.memNickname;
			document.getElementById("email-input").value = member.memEmail;
			document.getElementById("phone-input").value = member.memPhone;
			if (member.memGender == "0") {
				document.getElementById("male-input").checked = true;
			} else if (member.memGender == "1") {
				document.getElementById("female-input").checked = true;
			}
			document.getElementById("birth-input").value = member.memBirth;
		})

	document.getElementById('change-password').addEventListener('click', () => {
		location = './change_pass.html';
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
                <img src="${reader.result}">
              `;
			  		preview_pic.insertAdjacentHTML("beforeend", pic_html); // 加進節點
					
				});
				
		});
		
	});

	// 編輯資料
	const Mname = document.querySelector('#name-input');
	const nickname = document.querySelector('#nickname-input');
	const email = document.querySelector('#email-input');
	const phone = document.querySelector('#phone-input');
	const gender = document.getElementsByName('gender');
	const birth = document.querySelector('#birth-input');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('edit').addEventListener('click', () => {
		let picBase64;
		let preview_pic = document.querySelector('#preview-pic').innerHTML
		let preview_pic2 = preview_pic.replace('<img src="', "");
		picBase64 = preview_pic2.replace('">', "");
		console.log(picBase64);
		let selected_gender;
		if (gender[0].checked == true) {
			selected_gender = gender[0];
		} else if (gender[1].checked == true) {
			selected_gender = gender[1];
		}
		fetch('http://localhost:8080/simplefitness-servlet/member/edit', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memPicBase64: picBase64.trim(),
				memName: Mname.value,
				memNickname: nickname.value,
				memEmail: email.value,
				memPhone: phone.value,
				memGender: selected_gender.value,
				memBirth: birth.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					// location = './member_edit.html';
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