
$(function(){
	parseJson();
});

/*
 * JSON.stringify() JSON Object => JSON 형식의 문자열
 * JSON.parse() JSON형식의 문자열 > JSON Object
 * 
 * */
function parseJson(){
		//getJSON()은 비동기식으로 서버와 접속해 데이터를 불러올 때 사용하는 점은 같으나 JSON 타입의 데이터를 불러옵니다
				//bike.json이랑 통신 성공하면data가 넘어올것
	$.getJSON("json/bike.json" ,function(data){
		$.ajax({
			url:"bike.do?command=second_db",
			method:"post",
						//JSON형식의 문자열
			data:{"obj": JSON.stringify(data)},
			success:function(msg){
				// database에 저장을 성공하면 table을 만들자,
				alert(msg);//저장된 갯수를 여기에 띄워라..
				
						//json배열의 크기랑 같다면
				if(msg ==data.DATA.length){
					//$.each() 함수는 특정한 집합을 반복문 형태로 사용할 수 있습니다
					//callback은 앞의 데이터를 하나씩 꺼내오면 실행된다
					//$.each(data,function(key,val){
						
					$("table").attr("border","1");
					
					$("thead").append("<tr>"+
							"<th>"+data.DESCRIPTION.RENT_ID+"</th>"+
							"<th>"+data.DESCRIPTION.ADDR_GU+"</th>"+
							"<th>"+data.DESCRIPTION.CONTENT_ID+"</th>"+
							"<th>"+data.DESCRIPTION.CONTENT_NM+"</th>"+
							"<th>"+data.DESCRIPTION.NEW_ADDR+"</th>"+
							"<th>"+data.DESCRIPTION.CRADLE_COUNT+"</th>"+
							"<th>"+data.DESCRIPTION.LONGITUDE+"</th>"+
							"<th>"+data.DESCRIPTION.LATITUDE+"</th>"+
							"</tr>");
					
					var list = data.DATA;
					for(var i = 0; i < list.length; i++){
						var str = list[i];
						$("tbody").append("<tr>"+
								"<td>"+str.rent_id +"</td>"+
								"<td>"+str.addr_gu +"</td>"+
								"<td>"+str.content_id +"</td>"+
								"<td>"+str.content_nm +"</td>"+
								"<td>"+str.new_addr +"</td>"+
								"<td>"+str.cradle_count +"</td>"+
								"<td>"+str.longitude +"</td>"+
								"<td>"+str.latitude +"</td>"+
								"</tr>");
					}
				}
			},
			error:function(){
				alert("실패");
			}
		});
	});
}