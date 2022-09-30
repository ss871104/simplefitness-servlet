(() => {

	// 拿場館
	fetch("http://localhost:8080/simplefitness-servlet/gym/getAllGym")
	.then(resp => resp.json())
	.then(gym => {
		for (i = 0; i < gym["length"]; i++){
			sessionStorage.setItem(`'gym${gym[i].gymId}'`,`${gym[i].gymName}`);
		  let text = `
				<option value="${gym[i].gymId}">${gym[i].gymName}</option>`;
			$("#gym").append(text);
		}
	});
	
	
	const gym = document.querySelector('#gym');
	const selectedDate = document.querySelector('#dateRange');
	const search = document.querySelector('#queryCourse');
	
 
	document.getElementById('queryCourse').addEventListener('click', () => {
	
		if (gym.value == 0) {
			alert('請選擇場館');
			return;
		}
		if(selectedDate.value == "") {
			alert('請選擇日期');
			return;
		}
		// 列出現有課程
		fetch("http://localhost:8080/simplefitness-servlet/course/searchCourse", {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				gymId: gym.value,
				selectedDate: selectedDate.value
			}),
		})
		.then(resp => resp.json())
		.then(course => {
			if (course[0].successful) {
				for (i = 0; i < course["length"]; i++){
					let gymName = sessionStorage.getItem(`'gym${course[i].gymId}'`)
					let endTime = startTime	
					let text = `<tr>
								<td>${gymName}</td>
								<td>${course[i].startTime}, ${course[i].startTime}</td>
								<td>${course[i].courseListId}</td>
								<td>${course[i].empId}</td>
								<td>${course[i].status}</td>
								<td>${course[i].pubStatus}</td>
								<td><img class="listIcon" src="../../img/pencil_black.png" alt="編輯" title="編輯"></td>
								<td><img class="listIcon" src="../../img/trash_black.png" alt="刪除" title="刪除"></td>
							</tr>`;

					$("#courList").append(text);
				}
				$("#courList1").collapse('show');
			} else {
				window.alert(course[0].message);
			}
			
		});
		
	});

})();