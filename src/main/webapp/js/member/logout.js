(() => {
	document.getElementById('logout').addEventListener('click', () => {
		fetch("/simplefitness-servlet/member/logout")
			.then(body => {
				location = '../guest/home.html';
			});
	});
})();