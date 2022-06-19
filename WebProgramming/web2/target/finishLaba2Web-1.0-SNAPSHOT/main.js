var cordX;
var cordY;

var x;
var y;
var r;

function collect() {
    let yValue = Number(document.getElementById("yValue").value);
    let rValue = document.getElementsByName("rValue");
    let xValue = document.getElementById("xValue").value;

    if (rCheck(rValue)!==404) {
        if(xCheck(xValue)) {
            if (yCheck(yValue)) {
                sendHTTPAimRequest(xValue,yValue,rCheck(rValue));
            } else {
                document.getElementById("alerta").innerHTML = "Неверный Y";
            }
        } else {
            document.getElementById("alerta").innerHTML = "Неверный X";
        }
    } else {
        document.getElementById("alerta").innerHTML = "Неверный R";
    }
}

// function doteCollect(xValue,yValue,rValue) {
//
//     if (rCheck(rValue)!==404) {
//         if(xCheck(xValue)) {
//             if (yCheck(yValue)) {
//                 sendHTTPAimRequest(xValue,yValue,rCheck(rValue));
//             } else {
//                 alert("Неверный Y")
//             }
//         } else {
//             alert("Неверный X")
//         }
//     } else {
//         alert("Неверный R")
//     }
// }

function yCheck(Y) {
    if(Y<=5 && Y>=-5) {
        return true;
    } else {
        return false;
    }
}

function xCheck(X) {
    if (X.toString().length==0) {
        return false;
    } else if(isNaN(X)) {
        return false;
    } else if(parseFloat(X)>-5 && parseFloat(X)<5) {
        return true;
    } else {
        return false;
    }
}

function  rCheck(R) {
    let countR = 0;
    let i;
    for (i = 0; i < R.length; i++) {
        if (R[i].type == "radio" && R[i].checked) {
            return R[i].value;
        }
    }
    if (countR == 0) {
        return 404;
    }
}

function sendHTTPAimRequest(x, y, r){
    const requestURL = "/finishLaba2Web-1.0-SNAPSHOT/controller";
    const httpRequest = new XMLHttpRequest();
    const args = "?x=" + x + "&y=" + y + "&r=" + r + "&t=1";

    httpRequest.open("GET", requestURL + args);

    httpRequest.setRequestHeader("Content-Type", "application/json" );
    httpRequest.responseType = "text";

    httpRequest.onload = () => {
        document.getElementById("answer").innerHTML = httpRequest.responseText;
    };

    httpRequest.send();
}

document.querySelector('svg').addEventListener("mousedown", function (e) {
    cordX=e.offsetX
    cordY = e.offsetY
    detectClick()
});

function detectClick() {
    r = document.getElementsByName("rValue");
    if((rCheck(r)!==404)) {
        convertCoordinates();
        sendHTTPAimRequest(x,y,rCheck(r));
        setVisiblePoint();
    } else {
        document.getElementById("alerta").innerHTML = "Неверный R";
    }
}

function convertCoordinates() {
    changeXCord()
    changeYCord()
    x = convertToCoordinate(cordX)
    y = convertToCoordinate(cordY)
}

function convertToCoordinate(value){
    return (value*0.1125)/rCheck(r);
}

function changeXCord(){
    let centerX = 200
    if (cordX < centerX){
        cordX = -(centerX-cordX)
    }else{
        cordX = cordX-centerX
    }
}

function changeYCord(){
    let centerY = 200
    if (cordY>centerY){
        cordY = -(cordY-centerY)
    }else {
        cordY = centerY- cordY
    }
}

function setVisiblePoint(){
    let point = document.getElementById("point")
    point.setAttribute('cx',200+cordX)
    point.setAttribute('cy',200-cordY)
    point.setAttribute("visibility","visible")
}

function clear() {
    clearResultTable();
}

function clearResultTable() {
    sendHTTPCleanRequest();
}
function sendHTTPCleanRequest(){
    const requestURL = "/finishLaba2Web-1.0-SNAPSHOT/controller";
    const httpRequest = new XMLHttpRequest();
    const args = "?x=" + x + "&y=" + y + "&r=" + r + "&t=2";

    httpRequest.open("GET", requestURL + args);

    httpRequest.setRequestHeader("Content-Type", "application/json" );
    httpRequest.responseType = "text";

    httpRequest.onload = () => {
        document.getElementById("answer").innerHTML = httpRequest.responseText;
    };

    httpRequest.send();
}