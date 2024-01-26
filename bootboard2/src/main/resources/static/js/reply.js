// 댓글 기능 구현

let replyObject = {
	init: function(){
		$("#btn-save-reply").click(()=>{
			this.insertReply();   // this = 클릭한 대상 = 필수
		})		
	},
	insertReply: ()=>{
		alert("댓글 등록 요청됨");
		// boardId 가져오기
		let boardId = $("#boardId").val();
		// document.getElementById(replyContent).value
		// 댓글 내용
		let replyContent = $("#replyContent").val();
		// 댓글 data
		let reply = {
			content: replyContent    // content - 컨트롤러로 넘겨주는 값
		}
		
		$.ajax({
			type: "POST",
			url: "/reply/" + boardId,
			data: JSON.stringify(reply),  // 자바스크립트 객체를 문자화해서 json으로 변형
			contentType: "application/json; charset=utf-8"
		}).done(function(response){
			console.log(response);
		}).fail(function(error){
			alert("에러 발생: " + error);
		})
	}
}

replyObject.init();   // init() 함수 호출