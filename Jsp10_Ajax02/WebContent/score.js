

/*
  <한글 사용 인코딩>
  encodeURIComponent() : 모든 문자 인코딩
  encodeURI() : 주소줄에 사용되는 특수문자는 제외하고 인코딩
  decodeURIComponent() : 모든문자 디코딩 다시 원상태
 
 */
function getParameterValues(){
	var name = "name="+encodeURIComponent(document.getElementById("name").value);
	var kor = "kor="+document.getElementById("kor").value;
	var eng = "eng="+document.getElementById("eng").value;
	var math = "math="+document.getElementById("math").value;
	
	return name+"&"+kor+"&"+eng+"&"+math;
	
}


function load(){
	var url = "score.do?"+getParameterValues();
	alert(url);
	
	//var 가없으면 전역변수 자동
	httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange=callback;
	httpRequest.open("GET",url,true);			//true : 비동기 / false : 동기
	httpRequest.send();							//GET : send() / POST : send(string);
	
}

function callback(){
	alert("readyState : "+ httpRequest.readyState);
// 보통은 이렇게 같이씀 통신완료 && 성공	
//	if(httpRequest.readyState == 4 && httpRequest.status == 200 )
	if(httpRequest.readyState == 4){
		alert("status : "+ httpRequest.status);
		
		if(httpRequest.status == 200){
			
			// httpRequest.responseText : 응답받은 data (msg라고썻던것) 기본은 String 으로 받음
			var obj = JSON.parse(httpRequest.responseText);
			//JSON.parse
			//JSON.stringify
			document.getElementById("result").innerHTML = decodeURIComponent(obj.name)+"의 총점은" + obj.sum + ", 평균은 "+ obj.avg;
				
		}else{
			alert("데이터 계산 실패");
		}
	}
}
/*
XMLHttpRequest : javascript object. http를 통환 데이터 송수신지원
(XHR)
onreadystatechange=callback; readystate가 바뀔때마다 callback함수를 호출하자

<onreadystatechange>
-readyState
0 : uninitialized - 실행(load)되지 않음
1 : loading 
2 : loaded
3 : ineractive 통신되고있음
4 : complete 통신이 완료됨

-status 
200 : 성공 
403 : forbidden  권한이없을때
404 : not found 주소가 잘못됐을때
500 : internal server error 서버안에서 널포인트
...



*/