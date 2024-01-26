// 댓글 기능 구현

let replyObject = {
	init: function() {
		$("#btn-save-reply").click(() => {
			this.insertReply();   // this = 클릭한 대상 = 필수
		})
	},
	insertReply: () => {
		alert("댓글 등록 요청됨");
		// boardId 가져오기
		let boardId = $("#boardId").val();
		// document.getElementById(replyContent).value
		// 댓글 내용
		let replyContent = $("#replyContent").val();
		
		if(replyContent==""){
			alert("댓글을 입력해 주세요");
			$("#replyContent").focus();
			return false;
		}
		
		// 댓글 data
		let reply = {
			content: replyContent    // content - 컨트롤러로 넘겨주는 값
		}
		console.log(reply);
		
		let header = $("meta[name='_csrf_header']").attr('content');
		let token = $("meta[name='_csrf']").attr('content');

		$.ajax({
			type: "POST",
			url: "/reply/" + boardId,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			data: JSON.stringify(reply),  // 자바스크립트 객체를 문자화해서 json으로 변형
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			console.log(response);
			replyContent="";
			
			location.href="/board/"+boardId;
			
		}).fail(function(error) {
			alert("에러 발생: " + error);
		});
	}, // inserReply닫기
	
	deleteReply: function(boardId, replyId){
		alert("댓글 삭제 요청됨");
		
		let header = $("meta[name='_csrf_header']").attr('content');
		let token = $("meta[name='_csrf']").attr('content');
		
		$.ajax({
			type: "DELETE",
			url: "/reply/" + replyId,
						beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			}
			
		}).done((response)=>{
			console.log(response);
			location.href="/board/"+boardId;
		}).fail((error)=>{
			alert("에러 발생: "+ error)
		})
	}
}

replyObject.init();   // init() 함수 호출