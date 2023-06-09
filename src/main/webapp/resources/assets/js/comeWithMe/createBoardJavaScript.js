//let token = document.querySelector("meta[name='_csrf']").content;
//let header = document.querySelector("meta[name='_csrf_header']").content;
//
//function sendData(){
//	
//	let data = {
//			title : title.value,
//			content : content.value,
//			numOfPerson : numOfPerson.value,
//			place : place.value,
//			campingWay : campingWay.value,
//			ageAverage : ageAverage.value,
//			gender : gender.value
//	};
//	console.dir(data);
//	
//	let response = fetch("http://localhost:8080/comewithme/upload",{
//		method : 'post',
//		headers : {
//			'header' : header,
//			'X-CSRF-Token' : token,
//			'Content-Type' : 'application/json'
//		},
//		body: JSON.stringify(data)
//	}).then((response) =>{
//		console.log(response);
//		window.location.href="http://localhost:8080/comewithme/comeWithMeList";
//	})
//}

btnClick.addEventListener('click', e=>{
	
	if(title.value == ""){
		alert("제목을 입력해주세요.");
	}else if(content.value == ""){
		alert("내용을 입력해주세요.");
	}else if(numOfPerson.value == ""){
		alert("원하는 인원수를 입력해주세요.");
	}else if(place.value == ""){
		alert("원하는 장소를 선택해주세요.");
	}else if(campingWay.value == ""){
		alert("원하는 캠핑 방식을 선택해주세요.");
	}else if(ageAverage.value == ""){
		alert("원하는 연령대를 입력해주세요.");
	}else if(gender.value == ""){
		alert("원하는 성별을 입력해주세요.");
	}else{
		
	}
})





//해시태그
	$(document).ready(function() {
		var tag = {};
		var counter = 0;
		// 입력한 값을 태그로 생성한다.
		function addTag(value) {
			tag[counter] = value;
			counter++; // del-btn 의 고유 id 가 된다.
		}
		// tag 안에 있는 값을 array type 으로 만들어서 넘긴다.
		function marginTag() {
			return Object.values(tag).filter(function(word) {
				return word !== "";
			});
		}
		// 서버에 제공
		$("#tag-form").on("submit", function(e) {
			var value = marginTag(); // return array
			$("#rdTag").val(value);
			$(this).submit();
		});
		$("#tag").on("keypress",function(e) {
							var self = $(this);
							// 엔터나 스페이스바 눌렀을때 실행
							if (e.key === "Enter" || e.keyCode == 32) {
								var tagValue = self.val(); // 값
															// 가져오기
								// 해시태그 값 없으면 실행X
								if (tagValue !== "") {
									// 같은 태그가 있는지 검사한다. 있다면 해당값이
									// array 로 return 된다.
									var result = Object
											.values(tag)
											.filter(
													function(word) {
														return word === tagValue;
													})
									// 해시태그가 중복되었는지 확인
									if (result.length == 0) {
										$("#tag-list")
												.append(
														"<li class='tag-item'>"
																+ tagValue
																+ "<span class='del-btn' idx='"
																+ counter
																+ "'>x</span></li>");
										addTag(tagValue);
										self.val("");
									} else {
										alert("태그값이 중복됩니다.");
									}
								}
								e.preventDefault(); // SpaceBar 시
													// 빈공간이 생기지 않도록
													// 방지
							}
						});
		// 삭제 버튼
		// 인덱스 검사 후 삭제
		$(document).on("click", ".del-btn", function(e) {
			var index = $(this).attr("idx");
			tag[index] = "";
			$(this).parent().remove();
		});
	})