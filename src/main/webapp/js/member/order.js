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

				for(i = 0; i < order["length"]; i++){

                    let html =`
                    <tr>
		                <td width="100">${order[i].orderId}</td>
		                <td width="150">${order[i].gymId}</td>
		                <td width="100">${order[i].amount}</td>
		                <td width="200">${order[i].orderDate}</td>
		                <td width="120">${order[i].status}</td>
		                <td width="120"><button>訂單明細</button></td>
		            </tr>
                    `;
                    
                    $(".orderList").append(html);
                }
                
                
			})