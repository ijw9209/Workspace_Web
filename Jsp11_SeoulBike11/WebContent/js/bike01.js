
$(function(){
	getJson();
});

function getJson(){
	//getJSON : JSON 데이터를 가져오기
	$.getJSON("json/bike.json",function(data){
		//$.each() 함수는 특정한 집합을 반복문 형태로 사용할 수 있습니다
		$.each(data,function(key,val){
			if(key=="DESCRIPTION"){
				$("table").attr("border","1");
				
				$("thead").append("<tr>" + "<th>"+ val.RENT_ID+"</th>" +"<th>"+val.ADDR_GU +"</th>" + "<th>" + val.CONTENT_ID+"</th>" + "<th>"+val.CONTENT_NM +"</th>" +
						"<th>" + val.NEW_ADDR+"</th>" + "<th>" + val.CRADLE_COUNT +"</th>" + "<th>" + val.LONGITUDE + "</th>" +"<th>" + val.LATITUDE + "</th>" +
						"</tr>");
				
			}else if(key=="DATA"){
				var list = val;
				for(var i = 0; i < list.length; i++){
					var str = list[i];
					$("tbody").append("<tr>" + "<td>"+ str.rent_id+"</td>" +"<td>"+str.addr_gu +"</td>" + "<td>" + str.content_id+"</td>" + "<td>"+str.content_nm +"</td>" +
							"<td>" + str.new_addr+"</td>" + "<td>" + str.cradle_count +"</td>" + "<td>" + str.longitude + "</td>" +"<td>" + str.latitude + "</td>" +
							"<input type='hidden' name='bike' value='"+str.rent_id +"/" +str.addr_gu+"/"+str.content_id+"/"+str.content_nm+"/"+str.new_addr+"/"+str.cradle_count+"/"+
							str.longitude+"/"+str.latitude+"'/>"+
							"</tr>");		
					}
			}
		});
	});
}