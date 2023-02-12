const questionList = document.getElementById("question-list");
const loadQuestionObj = document.getElementById("loadQuestions");
const xhr = new XMLHttpRequest();
//单纯的告诉我们xhr对象，当我们发出GET请求的时候，要访问下面的网址
const xhr2 = new XMLHttpRequest();
xhr.responseType = "json"
xhr.onload = function(){
    //后端获取的数据在xhr.reponse中存储
    const questions = xhr.response
    for(const question of questions){
        const q = document.createElement("li");
        q.innerText = question.title;
        q.id = question.id;
        questionList.appendChild(q);
    }
}
loadQuestionObj.addEventListener('click',()=>{
    xhr.open('GET', 'http://o459371i96.zicp.vip:80/ks/2')
    //只有调用了send才能真正的获取数据。
    xhr.send();
})
const saveQuestionObj = document.getElementById("saveQuestion")
saveQuestionObj.addEventListener('click', ()=>{
    const postData = {
        title: document.getElementById("title").value,
        answer: document.getElementById("answer").value,
        difficulty: document.getElementById("difficulty").value
    }
    xhr2.open('POST', 'http://o459371i96.zicp.vip:80/save')
    xhr2.setRequestHeader("Content-Type", "application/json")
    xhr2.send(JSON.stringify(postData));
})