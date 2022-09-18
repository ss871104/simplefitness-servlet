(() => {
	document.getElementById('logout').addEventListener('click', () => {
		fetch("http://localhost:8080/simplefitness-servlet/staff/logout")
			.then(body => {
				location = '../guest/home.html';
			});
	});
})();