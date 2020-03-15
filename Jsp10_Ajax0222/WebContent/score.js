/*
  <한글 사용 인코딩>
  encodeURIComponent() : 모든 문자 인코딩
  encodeURI() : 주소줄에 사용되는 특수문자는 제외하고 인코딩
  decodeURIComponent() : 모든문자 디코딩 다시 원상태
 
 */
function getParameterValues(){
	var name = "name="+encodeURIComponent(document.getElementById("name"));
	var kor = "kor="+document.getElementById("kor");
	var eng = "eng="+document.getElementById("eng");
	var math = "math="+document.getElementById("math");
	
	return name + "&" + kor + "&" + eng + "&" + math;
}

function load(){
	var url = "score.do?"+getParameterValues();
	alert(url);
	
	httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange=callback;
	httpRequest.open("GET",url,true);
	httpRequest.send();
}