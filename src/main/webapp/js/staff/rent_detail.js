(() => {
// orderId get all
const orderId = sessionStorage.getItem('order');
fetch("http://localhost:8080/simplefitness-servlet/orderDetail/getOrderDetail", {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
        orderId: orderId
    }),
    })
.then(resp => resp.json())
.then(order => {
    for (i = 0; i < order["length"]; i++){
        let order_status = "";
        if (order[i].status == "0") {
            order_status = "尚未取貨";
        } else if (order[i].status == "1") {
            order_status = "已取貨";
        } else if (order[i].status == "2") {
            order_status = "已歸還";
        } else if (order[i].status == "3") {
            order_status = "逾時未取貨";
        } else if (order[i].status == "4") {
            order_status = "逾時未歸還";
        } else if (order[i].status == "5") {
            order_status = "商品損毀";
        }
      let text = `
          <tr>
            <td>${order[i].orderCode}</td>
            <td>${order[i].idvId}</td>
            <td>${order[i].prodName}</td>
            <td>${order_status}</td>
            <td><button type="button" class="btn btn-primary xxx" data-toggle="modal" data-target="#exampleModalCenter" name="edits" id="idv${order[i].orderCode}" value="${order[i].orderCode}">編輯</button></td>
          </tr>`;
    // 反引號 錢字號 大括號 三個為一包 出現在html 中的js
        $(".info").append(text);
    }

    // 編輯
    let xxx = document.querySelectorAll(".xxx");
    let order_code = document.querySelector('#id-input');
    let pickupTime = document.querySelector('#pick-up');
    let returnTime = document.querySelector('#return');
	for (let i = 0; i < xxx.length; i++) {
		xxx[i].addEventListener('click', () => {
            order_code.textContent = order[i].orderCode;
			if (order[i].status == "0") {
                document.getElementById("zero-input").checked = true;
            } else if (order[i].status == "1") {
                document.getElementById("one-input").checked = true;
            } else if (order[i].status == "2") {
                document.getElementById("two-input").checked = true;
            } else if (order[i].status == "3") {
                document.getElementById("three-input").checked = true;
            } else if (order[i].status == "4") {
                document.getElementById("four-input").checked = true;
            } else if (order[i].status == "5") {
                document.getElementById("five-input").checked = true;
            }

            pickupTime.value = order[i].pickupTime;
            returnTime.value = order[i].returnTime;
						
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
		} else if (status[3].checked == true) {
			selected_status = status[3];
		} else if (status[4].checked == true) {
			selected_status = status[4];
		} else if (status[5].checked == true) {
			selected_status = status[5];
		}
		if (selected_status == null) {
			errMsg.textContent = '狀態未選';
			return;
		}
        let returns = null;
        console.log(returnTime.value);
        if (returnTime.value != '') {
            returns = returnTime.value;
        }
        fetch("http://localhost:8080/simplefitness-servlet/rent/statusUpdateServlet", {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
        status: selected_status.value,
        pickupTime: pickupTime.value,
        returnTime: returns,
        orderCode: order_code.textContent
    }),
    })
.then(resp => resp.json())
.then(order => {
    errMsg.textContent = '';
				const { successful, message } = order;
				if (successful) {
					location = './rent_detail.html';
				} else {
					errMsg.textContent = message;
				}
})
    })

});

})();