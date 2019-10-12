
var randomScalingFactor = function() {
	return Math.ceil(Math.random() * 10.0) * Math.pow(10, Math.ceil(Math.random() * 5));
};

// Chart page home
var configThongKeHome = {
	type : 'line',
	data : {
		labels : [ 'January', 'February', 'March', 'April', 'May', 'June',
				'July' ],
		datasets : [
				{
					label : 'My First dataset',
					backgroundColor : window.chartColors.red,
					borderColor : window.chartColors.red,
					fill : false,
					data : [ randomScalingFactor(), randomScalingFactor(),
							randomScalingFactor(), randomScalingFactor(),
							randomScalingFactor(), randomScalingFactor(),
							randomScalingFactor() ],
				},
				{
					label : 'My Second dataset',
					backgroundColor : window.chartColors.blue,
					borderColor : window.chartColors.blue,
					fill : false,
					data : [ randomScalingFactor(), randomScalingFactor(),
							randomScalingFactor(), randomScalingFactor(),
							randomScalingFactor(), randomScalingFactor(),
							randomScalingFactor() ],
				} ]
	},
	options : {
		responsive : true,
		title : {
			display : true,
			text : 'Chart.js Line Chart - Logarithmic'
		},
		scales : {
			xAxes : [ {
				display : true,
			} ],
			yAxes : [ {
				display : true,
				type : 'logarithmic',
			} ]
		}
	}
};

// Chart page user
var configChartUser = {
	type : 'bar',
	data : {
		labels : [ 'Admin', 'trongson', 'halong', 'hanminh', 'manager',
				'admin112' ],
		datasets : [ {
			label : '# Lượt truy cập theo tháng',
			data : [ 12, 19, 3, 5, 2, 3 ],
			backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)' ],
			borderColor : [ 'rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)' ],
			borderWidth : 1
		} ]
	},
	options : {
		scales : {
			yAxes : [ {
				ticks : {
					beginAtZero : true
				}
			} ]
		}
	}
};

window.onload = function() {
	var chartHome = document.getElementById('thongke');
	var chartUser = document.getElementById('myChart');

	var view;
	if (chartHome != null) {
		view = chartHome.getContext('2d');
		window.myLine = new Chart(view, configThongKeHome);
	} else if (chartUser != null) {
		view = chartUser.getContext('2d');
		window.myLine = new Chart(view, configChartUser);
	}
};