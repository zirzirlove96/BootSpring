$(".submit-write input[type=submit]").click(addAnswer);

function addAnswer(e) {//e는 click의 이벤트를 나타낸다.
	//답변하기의 버튼을 눌렀을 때 막는 역할
	e.preventDefault();//이 부분이 서버로 가는 것을 막아 준다.
	console.log("click me");
	
	var queryString = $(".submit-write").serialize();
	console.log("query: "+queryString);
	
	var url = $(".submit-write").attr("action");
	console.log(url);
	
	$.ajax({
		type: 'post',
		url: url,
		data: queryString,
		dataType: 'json',
		success:function(data, status){
			console.log(data);
			var answerTemplate = $("#answerTemplate").html();
			var template = answerTemplate.format(data.writer.userId, data.formattedCreateDate, data.contents, data.id, data.id);
			$(".qna-comment-slipp-articles").prepend(template);
			
			$(".submit-write textarea").val("");
		},
		error:function(data,request,status,error){
			var userId = data.writer;
			if(userId==undefined){
				alert("로그인 먼저 해주세요!");
			}
			else alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n");
	       }
	});
}

String.prototype.format = function() {
	  var args = arguments;
	  return this.replace(/{(\d+)}/g, function(match, number) {
	    return typeof args[number] != 'undefined'
	        ? args[number]
	        : match
	        ;
	  });
	};
