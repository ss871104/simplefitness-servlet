(() => {
	// memId get all
	const memId = document.getElementById('memId');
	document.getElementById('search').addEventListener('click', () => {
		$(".info").html("");
		fetch("/simplefitness-servlet/rent/MemIdGetAllServlet", {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memId: memId.value,
			}),
		})
			.then(resp => resp.json())
			.then(order => {
				for (i = 0; i < order["length"]; i++) {
					let order_status = "";
					if (order[i].status == "0") {
						order_status = "已取消";
					} else if (order[i].status == "1") {
						order_status = "已付款";
					} else if (order[i].status == "2") {
						order_status = "未付款";
					}

					let text = `
          <tr>
            <td>${order[i].orderId}</td>
            <td>${order_status}</td>
            <td><button type="button" class="btn btn-primary xxx" data-toggle="modal" data-target="#exampleModalCenter" name="edits" id="idv${order[i].orderId}" value="${order[i].orderId}">編輯</button></td>
            <td><button type="button" class="btn btn-secondary" id="order${order[i].orderId}" value="${order[i].orderId}">進入</button></td>
          </tr>`;
					// 反引號 錢字號 大括號 三個為一包 出現在html 中的js
					$(".info").append(text);
				}

				// 點擊進入訂單明細
				for (i = 0; i < order["length"]; i++) {
					const button = document.querySelector(`#order${order[i].orderId}`);
					// js基本語法, id與class, 抓id要用#來抓
					button.addEventListener('click', () => {
						sessionStorage.setItem('order', button.value);
						location = './rent_detail.html';
					});
				}

				// 編輯
				let xxx = document.querySelectorAll(".xxx");
				let order_id = document.querySelector('#id-input');
				for (let i = 0; i < xxx.length; i++) {
					xxx[i].addEventListener('click', () => {
						order_id.textContent = order[i].orderId;
						if (order[i].status == "0") {
							document.getElementById("zero-input").checked = true;
						} else if (order[i].status == "1") {
							document.getElementById("one-input").checked = true;
						} else if (order[i].status == "2") {
							document.getElementById("two-input").checked = true;
						}

					})
				}

				const status = document.getElementsByName('status');
				document.getElementById('edit').addEventListener('click', () => {
					let selected_status;
					if (status[0].checked == true) {
						selected_status = status[0];
					} else if (status[1].checked == true) {
						selected_status = status[1];
					} else if (status[2].checked == true) {
						selected_status = status[2];
					}
					if (selected_status == null) {
						errMsg.textContent = '狀態未選';
						return;
					}
					fetch("/simplefitness-servlet/rent/OrderStatusUpdate", {
						method: 'POST',
						headers: { 'Content-Type': 'application/json' },
						body: JSON.stringify({
							status: selected_status.value,
							orderId: order_id.textContent
						}),
					})
						.then(resp => resp.json())
						.then(order => {
							errMsg.textContent = '';
							const { successful, message } = order;
							if (successful) {
								location = './rent.html';
							} else {
								errMsg.textContent = message;
							}
						})
				})

			});
	});

})();