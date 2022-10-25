(() => {
	// 一進來get gym
	fetch("http://localhost:8080/simplefitness-servlet/gym/getAllGym")
		.then(resp => resp.json())
		.then(gym => {
			for (i = 0; i < gym["length"]; i++) {
				sessionStorage.setItem(`'gym${gym[i].gymId}'`, `${gym[i].gymName}`);

				let text = `
		  		<option value="${gym[i].gymId}" id="gym${gym[i].gymId}">${gym[i].gymName}</option>
		  		`;

				$(".gym").append(text);

			}

		});

	// get idv_product by selecting gym
	const gymId = document.querySelector("#gym-input");
	$("#gym-input").change(function() {
		$(".info").html("");

		fetch("http://localhost:8080/simplefitness-servlet/idvProduct/SelectGymGetProd", {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				prodId: sessionStorage.getItem('product'),
				gymId: gymId.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {

				for (i = 0; i < body["length"]; i++)  {
					let gymName = sessionStorage.getItem(`'gym${body[i].gymId}'`);
					let idvStatus = "";
					if (body[i].status == "0") {
						idvStatus = '報廢';
					} else if (body[i].status == "1") {
						idvStatus = '庫存中';
					} else if (body[i].status == "2") {
						idvStatus = '借出中';
					} else if (body[i].status == "3") {
						idvStatus = '維修中';
					}
					let text = `
                    <tr class="idv">
                        <td>${body[i].idvId}</td>
                        <td>${idvStatus}</td>
                        <td>${gymName}</td>
                        <td><button type="button" class="btn btn-primary xxx" data-toggle="modal" data-target="#editIdvProduct" name="edits" id="idv${body[i].idvId}" value="${body[i].idvId}">編輯</button></td>
                    </tr>`;

					$(".info").append(text);

				}

				// 編輯
				let xxx = document.querySelectorAll(".xxx");
				for (let i = 0; i < xxx.length; i++) {
					xxx[i].addEventListener('click', () => {
						let xxxId;
						xxxId = xxx[i].closest(".idv").children[0].textContent;
						sessionStorage.setItem('idvId', xxxId);
						if (xxx[i].closest(".idv").children[1].textContent == "報廢") {
							document.getElementById("zero-input").checked = true;
						} else if (xxx[i].closest(".idv").children[1].textContent == "庫存中") {
							document.getElementById("one-input").checked = true;
						} else if (xxx[i].closest(".idv").children[1].textContent == "借出中") {
							document.getElementById("two-input").checked = true;
						} else if (xxx[i].closest(".idv").children[1].textContent == "維修中") {
							document.getElementById("three-input").checked = true;
						}
						console.log(gymId.value);
						// document.getElementById(`gym${gymId.value}`).setAttribute('selected', 'selected');

					})
				}


				const status = document.getElementsByName('status');
				const gym = document.getElementById('gym-input2');
				const errMsg = document.querySelector('#errMsg');

				document.getElementById('edit').addEventListener('click', () => {

					let status_update;
					if (status[0].checked == true) {
						status_update = status[0];
					} else if (status[1].checked == true) {
						status_update = status[1];
					}
					else if (status[2].checked == true) {
						status_update = status[2];
					}
					else if (status[3].checked == true) {
						status_update = status[3];
					}

					if (status_update == null) {
						errMsg.textContent = '狀態未更新';
						return;
					}
					console.log(gym.value);
					fetch('http://localhost:8080/simplefitness-servlet/idvProduct/editIdvProdGym', {
						method: 'POST',
						headers: { 'Content-Type': 'application/json' },
						body: JSON.stringify({

							idvId: sessionStorage.getItem('idvId'),
							gymId: gym.value,
							status: status_update.value

						}),
					})
						.then(resp => resp.json())
						.then(body => {
							errMsg.textContent = '';
							const { successful, message } = body;
							if (successful) {
								location = './idvproduct.html';
							} else {
								errMsg.textContent = message;
							}
						})
				})

			})

		// var statusinput = $('input[name="status"]').filter(':checked').val();
		// console.log(statusinput);
		// var gyminput=('#gym-input option:selected').val(); 
		// console.log(gyminput);

		// fetch("http://localhost:8080/simplefitness-servlet/idvProduct/SelectGymGetProd", {
		//     method: 'POST',
		//     headers: { 'Content-Type': 'application/json' },
		//     body: JSON.stringify({
		//         gymId: gyminput,
		//         status: statusinput
		//     })
		// })
	})

	// 新增資料
	const status = document.getElementsByName('status2');
	const gym = document.getElementById('gym-input3');
	const errMsg = document.querySelector('#errMsg');

	document.getElementById('add').addEventListener('click', () => {

		let status_update;
		if (status[0].checked == true) {
			status_update = status[0];
		} else if (status[1].checked == true) {
			status_update = status[1];
		}
		else if (status[2].checked == true) {
			status_update = status[2];
		}
		else if (status[3].checked == true) {
			status_update = status[3];
		}

		if (status_update == null) {
			errMsg.textContent = '狀態未更新';
			return;
		}
		console.log(gym.value);
		fetch('http://localhost:8080/simplefitness-servlet/idvProduct/idvProdInsert', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({

				prodId: sessionStorage.getItem('product'),
				gymId: gym.value,
				status: status_update.value

			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = './idvproduct.html';
				} else {
					errMsg.textContent = message;
				}
			})
	})

})();
