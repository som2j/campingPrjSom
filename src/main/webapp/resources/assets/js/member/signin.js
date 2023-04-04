var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

/*
		function idCheckDB() {

			$.ajax({
				url : "idCheck",
				type : "post",
				data : {
					userId : id.value

				},beforeSend : function(xhr){
					xhr.setRequestHeader(header, token);
				},
				success: function(data){
					
					console.log("통신 성공");
					
					if(data === 'notToExist'){
						alert('사용 가능한 아이디입니다');
					}
					else{
						alert('이미 존재하는 아이디입니다');
					}
					
					}

			});

		}*/
	
$(document).ready(function(){
		$("#id").on("focusout", function(){
			
			$.ajax({
				url : "idCheck",
				type : "post",
				data : {
					userId : id.value

				},beforeSend : function(xhr){
					xhr.setRequestHeader(header, token);
				},
				success: function(data){
					
					console.log("통신 성공");
					if(id.value === ''|| id.length==0){
						$('#id_div').html('<a style="color:red;">ID를 입력해주세요.</a>');
					}
					
				
					else if(data === 'notToExist'){
						$('#id_div').html('<a>사용 가능한 ID입니다.</a>');
					}
					else{
						$('#id_div').html('<a style="color:red;">이미 존재하는 ID입니다.</a>');
					}
					
					}

			});
			

			
		}
	
		);
});





		
		function signIn() {

			if(!id.value || !password.value || !nameData.value || !tell.value || !gender.value || !birth.value)
				{
				alert('모든 항목을 입력해 주시기 바랍니다');
				return 0;
				}
				
			console.log(id.value);
			console.log(nameData.value);
			console.log(birth.value);
			console.log(tell.value);
			
			$.ajax({
				url : "signInData",
				type : "post",
				data : {
					userId : id.value,
					password: password.value,
					name : nameData.value,
					tell : tell.value,
					gender : gender.value,
					birth : birth.value
						

				},beforeSend : function(xhr){
					xhr.setRequestHeader(header, token);
				},
				success: function(data){
					
					console.log("통신 성공");
					
					if(data === 'success')
					{
						alert('회원가입 성공!');
						window.location.href="http://localhost:8080/members/login";	
					}
					else if(data === 'fail')
					{
						alert('회원가입 실패!');
					}
					else if(data === 'idError')
					{
						alert('중복된 아이디입니다!');
					}
					else{
						alert('회원가입 실패!');
					}
						
					
					}

			});

		}
		
		
		
		
		
		
	/*	idCheck.addEventListener('click',e=>{
			idCheckDB();
		        
		        });*/
		
		btnSign.addEventListener('click',e=>{
			signIn();
			
		        
		        });