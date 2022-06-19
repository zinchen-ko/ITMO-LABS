function check() {

    let X = document.getElementById("X").value;
    let Y = document.getElementById("Y").value;
    let R = document.getElementById('select');

    if (checkX(X) == 100) {
        document.getElementById("warning").innerHTML = 'X [-5...5]';
    } else if (checkY(Y) == 100) {
        document.getElementById("warning").innerHTML = 'Y [-5...5]';
    } else {
        let request = '?x=' + X + '&y=' + Y + '&r=' + R.value +'&t=1';
        fetch("main.php" + request, {
            method: "GET",
            headers: {"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"},
        }).then(response => response.text()).then(response=>{
            document.getElementById("answer").innerHTML = response;
            document.getElementById("warning").innerHTML = "";
        })
    }
}
function checkX(X) {
    if (X.length >0){
        if (X <= 5) {
            if (X>= -5) {
                return X;
            } else {
                return 100;
            }
        } else {
            return 100;
        }
    } else {
        return 100;
    }
}
function checkY(Y) {
    if (Y.length >0){
        if (Y <= 5) {
            if (Y>= -5) {
                return Y;
            } else {
                return 100;
            }
        } else {
            return 100;
        }
    } else {
        return 100;
    }
}
function clear() {
    alert("fxgcvxcv");
    let request = '?t=2' ;
    fetch("main.php" + request, {
        method: "GET",
        headers: {"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"},
    }).then(response => response.text()).then(response=>{
        document.getElementById("warning").innerHTML = response;})
    document.getElementById("warning").innerHTML = "lol";
}
