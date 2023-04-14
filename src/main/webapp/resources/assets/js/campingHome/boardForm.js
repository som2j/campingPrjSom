let token = document.querySelector("meta[name='_csrf']").content;
let header = document.querySelector("meta[name='_csrf_header']").content;

const form = document.getElementById('form');

form.addEventListener('submit', async (e)=>{
    e.preventDefault();


    const formData = new FormData(form);
    const photos = document.querySelector('input[type="file"][multiple]');
    //let title = document.getElementById('title').value;
    //let text = document.getElementById('text').value;
    //let hashtag = document.getElementById('hashtag').value;

    formData.append('title','title');
    formData.append('text','text');
    formData.append('hashtag','hashtag');
    for (let i = 0; i< photos.files.length; i++){
        formData.append(`photos_${i}`,photos.files[i]);
    }
    await fetch("http://localhost:8081/campingHome/board/new",{

    //formData.append('title','yoon');
    //formData.append('text','hyuk');
    //formData.append('hashtag','hashtag');
    formData.append(`files`, photos.files);

    // for (let i = 0; i < photos.files.length; i++){
    //     formData.append(`files`, photos.files);
    // }


    // let data = {
    //     title: title.value,
    //     text: text.value,
    //     hashtag:hashtag.value,
    // }

    await fetch("http://localhost:8080/campingHome/board/new",{
        method : 'POST',
        //redirect: 'follow',
        headers: {
            'header': header,
            'X-CSRF-Token': token,

        },
        body : formData,
    })
        .then((response)=>{
            let bdidx = response.text();
            console.dir(bdidx);
            //window.location.href = "http://localhost:8080/campingHome/boards";
            let num = response.text();
            window.location.href = "http://localhost:8080/campingHome/boards?page=1";
        }).catch((err)=>{
            response.json()
            //window.location.href = "http://localhost:8080/campingHome/boards?page=1";
        }).then(result => console.log("Success:", result))
        .catch((err)=>{
            console.log("err",err);
        })

});
