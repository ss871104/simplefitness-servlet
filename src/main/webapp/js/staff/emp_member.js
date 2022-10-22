(() => {
	// 一進來get all
	fetch("http://localhost:8080/simplefitness-servlet/member/getAll")
	.then(resp => resp.json())
	.then(member => {
		for (i = 0; i < member["length"]; i++){
			let status = "";
			if (member[i].memStatus == "0") {
				status = "無會籍"
			} else if (member[i].memStatus == "1") {
				status = "有會籍"
			}
			if (member[i].memNickname == null) {
				member[i].memNickname = "";
			}
		  let text = `
		  	<tr>
				<td>${member[i].memId}</td>
				<td>${member[i].memName}</td>
				<td>${member[i].memNickname}</td>
				<td>${member[i].memRegister}</td>
				<td>${status}</td>
				<td><button type="button" class="btn btn-secondary" id="member${member[i].memId}" value="${member[i].memId}">進入</button></td>
	  		</tr>`;

			$(".info").append(text);
		}
		// 點擊進入
		for (i = 0; i < member["length"]; i++){
		const button = document.querySelector(`#member${member[i].memId}`);
			button.addEventListener('click', () => {
				sessionStorage.setItem('member', button.value);
				location = './emp_member_edit.html';
			});
		}
	});

})();