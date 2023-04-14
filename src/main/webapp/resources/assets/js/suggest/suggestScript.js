let classAddEventListener= function(cssSelector,ev){
    
    let element = $(cssSelector);
    for(let i =0 ; i<element.length;i++){
        element[i].addEventListener('click',ev);
    }
}

let questionList =[
    "몇 명이서 캠핑을 가시나요?",
    "어디로 가고 싶나요(지역)?",
    "어디로 가고 싶나요(산/바다)?"
]

let selectList = [
    ["혼자","2인 ~ 3인","4인 ~ 5인","6인 이상"],
    ["경기도","강원도","충청남도", "충청북도","전라남도", "전라북도","경상남도", "경상북도","제주특별자치도"],
    [],
    ["바다","산","무관"]
]

let 경기도 = ["수원시", "성남시", "고양시", "용인시", "부천시", "안산시", "안양시", "남양주시", "화성시", "평택시", "의정부시", "시흥시", "파주시", "광명시", "김포시", "군포시", "광주시", "이천시", "양주시", "오산시", "구리시", "안성시", "포천시", "의왕시", "하남시", "여주시", "여주군", "양평군", "동두천시", "과천시", "가평군", "연천군"]
let 강원도 = ["춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군", "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군"]
let 충청북도 = ["청주시", "충주시", "제천시", "청원군", "보은군", "옥천군", "영동군", "진천군", "괴산군", "음성군", "단양군", "증평군"]
let 충청남도 = ["천안시", "공주시", "보령시", "아산시", "서산시", "논산시", "계룡시", "당진시", "당진군", "금산군", "연기군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군"]
let 전라북도 = ["전주시", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군", "무주군", "장수군", "임실군", "순창군", "고창군", "부안군"]
let 전라남도 = ["목포시", "여수시", "순천시", "나주시", "광양시", "담양군", "곡성군", "구례군", "고흥군", "보성군", "화순군", "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군", "영광군", "장성군", "완도군", "진도군", "신안군"]
let 경상북도 = ["포항시", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시", "상주시", "문경시", "경산시", "군위군", "의성군", "청송군", "영양군", "영덕군", "청도군", "고령군", "성주군", "칠곡군", "예천군", "봉화군", "울진군", "울릉군"]
let 경상남도 = ["창원시", "마산시", "진주시", "진해시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시", "의령군", "함안군", "창녕군", "고성군", "남해군", "하동군", "산청군", "함양군", "거창군", "합천군"]
let 제주특별자치도 = ["제주시", "서귀포시", "북제주군", "남제주군"]

let answer =[];

let selectIndex = 0;

let token = document.querySelector("meta[name='_csrf']").content;
let header = document.querySelector("meta[name='_csrf_header']").content;

let suggestPlaceURL=()=>{

    let url = "http://localhost:8081/suggest/suggestPlace";
    let data={
        who: answer[0],
        location : answer[1],
        terrain : answer[2]
    }
    data=JSON.stringify(data);

    let xhr = new XMLHttpRequest();
    
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader(header,token);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.dir(this.responseText);
        }
    };
    xhr.send(data);

    location.href = url;
}




let changeSelectQuestion= ()=>{
    
    if(selectIndex==4){
        suggestPlaceURL();
        return;
    }

    questionnaire.replaceChildren();
    if(selectIndex==2){
        for(let i=0; i<eval(answer[1]).length;i++){
            question.innerText=questionList[selectIndex];
            let option = createElement('li', {prop:{className:'option', style:'float:left; margin-right:20px'}});
            let a = createElement('a',{prop:{ className:"button primary small", innerText:eval(answer[1])[i],id:option}})
            let br= createElement('br');
            option.prepend(a);
            option.prepend(br);
            option.addEventListener('click',ev=>{
                answer.push(a.innerText);
            })
    
            $('.questionnaire').append(option);
        }
    
        classAddEventListener('.option',changeSelectQuestion)
        selectIndex++;
        return;
    }


    //selectIndex = 0,1,3
    let selectIdx=selectIndex;
    if(selectIndex==3){selectIdx==selectIndex-1; console.dir(selectIdx);} 
    for(let i=0; i<selectList[selectIndex].length;i++){
        question.innerText=questionList[selectIdx];
        let option = createElement('li', {prop:{className:'option'}});
        let a = createElement('a',{prop:{ className:"button primary small fit", innerText:selectList[selectIndex][i],id:option}})
        let br= createElement('br');
        option.prepend(a);
        option.prepend(br);
        option.addEventListener('click',ev=>{
            answer.push(a.innerText);
        })

        $('.questionnaire').append(option);
    }

    classAddEventListener('.option',changeSelectQuestion)
    selectIndex++;
    
}

classAddEventListener('.option',changeSelectQuestion)
changeSelectQuestion();






let MYKEY ="Xoc%2Fw%2B%2FnadLCHKskLv3utx5JAtAnnq2P0fPezsNoWQcAIrAcAlIVL%2FQAZRKISe8eCMimo5u98Qhpe5vAyT%2BwZg%3D%3D";
let GoCampingURL= "http://apis.data.go.kr/B551011/GoCamping/basedList?serviceKey=";
let _type = "&_type=json";


// "https://cros-anywhere.herokuapp.com/"+

// get방식으로 해야함 -> cros 오류로 인하여 get으로 작성해야함 -> suggest/select에서 paramsList를 받아서 url를 작성하고 get으로 받아서 data parsing을 해야함
// get으로 받는 것 까지는 성공적으로 하였음 -> post 실패 후 원인은 서버에서 cros를 방지하기 위해 post방식을 막은 것으로 생각이 듬
// server에 데이터를 업데이트,추가 하는 것이 아니고 서버에 있는 데이터를 불러오기만하기 때문에 get으로 하는 것이 옳은 방식임

let result = fetch(GoCampingURL+MYKEY+"&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=TestApp"+_type, {
	method : 'get',
	})
result.then(function(response) {
    console.log('response', response)
    console.log('header', response.headers.get('Content-Type'))
    return response.text()
}).then(function(text) {
    console.log('got text', text)
}).catch(function(ex) {
    console.log('failed', ex)
});