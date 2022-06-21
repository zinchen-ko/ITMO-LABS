let cordX
let cordY
let area = document.getElementById("right_side")

area.addEventListener("click",function (event) {
    cordX = event.offsetX
    cordY = event.offsetY
    htmlIsClicked(cordX,cordY)
})

function htmlIsClicked(cordX,cordY){
    let width = 400;
    let height = 400;
    const pixelsInOneRadiusX = (width + 3) / 2.5;
    const pixelsInOneRadiusY = (height + 3) / 2.5;
    const numberOfRadiusInX = (cordX - width/2) / pixelsInOneRadiusX;
    const numberOfRadiusInY = - (cordY - height/2) / pixelsInOneRadiusY;
    const yValue = (numberOfRadiusInY * getRLabel().value).toFixed(4);
    const xValue = Number((numberOfRadiusInX*getRLabel().value).toFixed(4));
    sendHTTPAimRequest(xValue, yValue, getRLabel().value);
}

function sendHTTPAimRequest(x, y, r){
    getFakeXLabel().value = x;
    getFakeYLabel().value = y;
    getFakeRLabel().value = r;
    getFakeSendButton().click();
}

function getFakeXLabel(){
    let form = document.querySelector(".invisible").firstChild.nextSibling;
    return form.firstChild.nextSibling.nextSibling.nextSibling;

}

function getFakeYLabel(){
    return  getFakeXLabel().nextSibling;
}

function getFakeRLabel(){
    return  getFakeYLabel().nextSibling;
}

function getRLabel(){
    const parent = document.querySelector(".x_side");
    return parent.lastChild.previousSibling;
}

function getFakeSendButton(){
    return  getFakeRLabel().nextSibling;
}