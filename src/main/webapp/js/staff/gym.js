(() => {
	// 一進來get all
	fetch("http://localhost:8080/simplefitness-servlet/gym/getAllGym")
	.then(resp => resp.json())
	.then(gym => {
		for (i = 0; i < gym["length"]; i++){
			console.log(gym[i])
		  let v = gym[i].gymId;
		  let text = `
		  	<tr>
				<td>${gym[i].gymName}</td>
				<td>${gym[i].phone}</td>
				<td>${gym[i].openDate}</td>
				<td>${gym[i].openTime} - ${gym[i].closeTime}</td>
				<td>${gym[i].maxPeople}</td>
				<td><button type="button" class="btn btn-secondary" id="gym${gym[i].gymId}" value="${gym[i].gymId}">進入</button></td>
	  		</tr>`;

			$(".info").append(text);
			console.log(document.querySelector(`#gym${gym[i].gymId}`).value);
		}
		// 點擊進入
		for (i = 0; i < gym["length"]; i++){
		const button = document.querySelector(`#gym${gym[i].gymId}`);
			button.addEventListener('click', () => {
				sessionStorage.setItem('gym', button.value);
				location = './gym_edit.html';
			});
		}
	});

	

	// 點擊新增
	const name = document.querySelector('#name-input');
	const address = document.querySelector('#address-input');
	const phone = document.querySelector('#phone-input');
	const open_date = document.querySelector('#publish-input');
	const open_time = document.querySelector('#open-input');
	const close_time = document.querySelector('#close-input');
	const count = document.querySelector('#count-input');
	const intro = document.querySelector('#intro-input');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('add').addEventListener('click', () => {
		if (count.value == "") {
			errMsg.textContent = '人數上限未填';
			return;
		}
		fetch('http://localhost:8080/simplefitness-servlet/gym/addGym', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				gymName: name.value,
				address: address.value,
				phone: phone.value,
				openDate: open_date.value,
				openTime: open_time.value,
				closeTime: close_time.value,
				maxPeople: count.value,
				intro: intro.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = '../../html/staff/gym.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});
	
})();