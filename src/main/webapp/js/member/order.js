fetch("http://localhost:8080/simplefitness-servlet/order/getMemOrder", {
	method: 'POST',
	headers: { 'Content-Type': 'application/json' },
	body: JSON.stringify({
		memId: 1
	}),
})
	.then(resp => resp.json())
	.then(order => {

		$(".orderList").html("");

		for (i = 0; i < order["length"]; i++) {
			if (order[i].status == 0) {
				order[i].status = "已取消"
			} else if (order[i].status == 1) {
				order[i].status = "已付款"
			} else if (order[i].status == 2) {
				order[i].status = "未付款"
			}

			let html = `
                    <tr class="order" data-toggle="modal" data-target="#exampleModalCenter" id="${order[i].orderId}">
		                <td>${order[i].orderId}</td>
		                <td>${order[i].gymName}</td>
		                <td>$${order[i].amount}</td>
		                <td>${order[i].orderDate.replace("T"," ")}</td>
		                <td>${order[i].status}</td>
		                <td><button class="cancel">取消訂單</button></td>
		            </tr>
                    `;

			$(".orderList").append(html);
		}

		let cancels = document.querySelectorAll(".cancel");
		for (let i = 0; i < cancels.length; i++) {
			cancels[i].addEventListener("click", (e) => {
				e.stopPropagation();
				let status = cancels[i].closest(".order").children[4].textContent;
				if (status !== "未付款") {
					alert("訂單已完成 或 已取消")
				} else {
					let check = confirm('確定取消訂單？');
					if (check) {
						orderId = cancels[i].closest(".order").children[0].textContent;
						fetch('http://localhost:8080/simplefitness-servlet/order/CancelOrder', {
							method: 'POST',
							headers: { 'Content-Type': 'application/json' },
							body: JSON.stringify({
								orderId: orderId,
							}),
						})
							.then(resp => resp.json())
							.then(body => {
								if (body == true) {
									alert("取消成功！")
									window.location.reload();
								}
							});
					}
				}

			})
		}
		
		for (let i = 0; i < order["length"]; i++) {
			let orders = document.getElementById(`${order[i].orderId}`);

			orders.addEventListener("click", () => {
				orderId = orders.children[0].textContent;
				console.log(orderId);
				fetch("http://localhost:8080/simplefitness-servlet/orderDetail/getOrderDetail", {
					method: 'POST',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify({
						orderId: orderId
					}),
				})
					.then(resp => resp.json())
					.then(detail => {
						$(".detailList").html("");

						for (i = 0; i < detail["length"]; i++) {
							if (detail[i].status == 0) {
								detail[i].status = "尚未取貨"
							} else if (detail[i].status == 1) {
								detail[i].status = "已取貨"
							} else if (detail[i].status == 2) {
								detail[i].status = "已歸還"
							}else if (detail[i].status == 3) {
								detail[i].status = "逾時未取貨"
							}else if (detail[i].status == 4) {
								detail[i].status = "逾時未歸還"
							}else if (detail[i].status == 5) {
								detail[i].status = "商品損毀"
							}
							
							if(detail[i].pickupTime == undefined){
								detail[i].pickupTime = "-"
							}
							if(detail[i].returnTime == undefined){
								detail[i].returnTime = "-"
							}
							
							let html = `
		                    <tr>
		                          <td>${detail[i].prodName}</td>
		                          <td>${detail[i].idvId}</td>
		                          <td>${detail[i].pickupTime}</td>
		                          <td>${detail[i].returnTime}</td>
		                          <td>${detail[i].status}</td>
		                        </tr>
		                    `;

							$(".detailList").append(html);
						}

					});
			})


		}

	})


