// 날짜를 변수화
const date = new Date();
console.log(date);
let year = date.getFullYear();   // 2024
let month = '0' + date.getMonth() + 1;   // 1
let day = '0' + date.getDate();        // 25
month = month.substring(1) // substring(인덱스)인덱스부터 끝까지 추출
day = day.substring(1);

let today = year + month + day;

$.ajax({
	type: "GET",
	url: "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=Czv3Me9Ivw6piA470yefflffDGzAsw9tObOp4T5OariNl8C3CGpaUt9JHcev%2BlOlh8%2FTj2fcYguzY86yh87F%2Fg%3D%3D&pageNo=1&numOfRows=1000&dataType=JSON&base_date=" + today + "&base_time=0600&nx=55&ny=127",
	// data: ,
	success: function(data) {
		console.log(data);
		console.log(data.response.body.items.item[3].obsrValue);
		let item = data.response.body.items.item[3];
		let content = "날짜: " + item.baseDate +
			", 발표시각: " + item.baseTime +
			", 기온: " + item.obsrValue;
		$('.result').text(content);
	},
	error: function(error) {
		console.log(error);
	}

});