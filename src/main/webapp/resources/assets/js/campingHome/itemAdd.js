let token = document.querySelector("meta[name='_csrf']").content;
let header = document.querySelector("meta[name='_csrf_header']").content;

const form = document.getElementById('itemName');

form.addEventListener('submit', async (e)=>{
    e.preventDefault();

    let data = {
        itemName:itemName.value
    }


    await fetch("http://localhost:8080/campingHome/board/new" ,{
        method : 'POST',
        headers: {
            'header': header,
            'X-CSRF-Token': token,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
        .then((response)=>{
            console.log(response);
        }).catch((err)=>{
            console.log("err",err);
        })
    window.location.reload();
});
