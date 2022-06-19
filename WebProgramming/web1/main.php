<?php
$startWork = microtime(true);
date_default_timezone_set('Europe/Moscow');
@session_start();
if (!isset($_SESSION["answer"])){
    $_SESSION["answer"] = array();
}

if($_GET["t"]==1){
    $x=$_GET["x"];
    $y=$_GET["y"];
    $r=$_GET["r"];
    $nowDate=date("Y-m-d H:i:s");


    if(checkValid($x,$y,$r)){
        array_unshift($_SESSION["answer"],"<tr>
                                             <th>".$x."</th>
                                             <th>".$y."</th>
                                             <th>".$r."</th>
                                             <th>".checkPosition($x,$y,$r)."</th>
                                             <th>".$nowDate."</th>
                                             <th>".((microtime(true) - $startWork)) ." сек</th>
                                             </tr>");
        printText();

    }else{
        array_unshift($_SESSION["answer"],"<tr>
                                                 <th>Ошибка валидации</th>
                                                 <th>Ошибка валидации</th>
                                                 <th>Ошибка валидации</th>
                                                 <th>Ошибка валидации</th>
                                                 <th>Ошибка валидации</th>
                                                 <th>Ошибка валидации</th>
                                                 </tr>");
        printText();

    }

}else if($_GET["t"]==2){
    echo "ZERO";
    session_destroy();
}else{
    if(count($_SESSION["answer"])==0){
        echo "<td id='nullAnswer'><center><p >Данных еще нет</p></center></td>";
    }else{
        printText();}

}


function printText(){
    echo "<table>
     <tr>
           <th>x</th>
           <th>y</th>
           <th>r</th>
           <th>Попадение </th>
           <th>Время запроса</th>
           <th>Время исполнения скрипта</th>
           </tr>";
    foreach ($_SESSION["answer"] as $answers) echo $answers;
    echo "</table>";
}




function checkValid($x,$y,$r){
    if (($x>=-5 && $x<=5) && ($y>=-5 && $y<=5) && ($r==1 && $r==2 && $r==3 && $==4 && $r==5)) {
        return true;
    } else {
        return false;
    }
}

function checkPosition($x1, $y1, $r1) {

    if($r1==1) {
        if (($x1<=0.5 && $x1>=0) && $y1=0) {
            return "Успех";
        } else if (($y1<=0.5 && $y1>=0) && $x1=0) {
            return "Успех";
        } else if (($x1>= -0.5 && $x1<=0) && $y1=0) {
            return "Успех";
        } else if(($x1>= -0.5 && $x1<=0) && ($y1<=0.5 && $y1>=0)) {
            return "Успех";
        } else if(($x1<=0.5 && $x1>=0) && ($y1<=0 && $y1>=-1)) {
            return "Успех";
        } else if(($x1>=-0.5 && $x1<=0) && ($y1<=0 && $y1>=-1)) {
            return "Успех";
        } else {
            return "Провал";
        }
    } else if($r1==2) {
        if (($x1<=1 && $x1>=0) && $y1=0) {
            return "Успех";
        } else if (($y1<=1 && $y1>=0) && $x1=0) {
            return "Успех";
        } else if (($x1>= -1 && $x1<=0) && $y1=0) {
            return "Успех";
        } else if(($x1>= -1 && $x1<=0) && ($y1<=1 && $y1>=0)) {
            return "Успех";
        } else if(($x1<=1 && $x1>=0) && ($y1<=0 && $y1>=-2)) {
            return "Успех";
        } else if(($x1<=-1 && $x1<=0) && ($y1<=0 && $y1>=-2)) {
            return "Успех";
        } else {
            return "Провал";
        }
    } else if($r1==3) {
        if (($x1 <= 1.5 && $x1 >= 0) && $y1 = 0) {
            return "Успех";
        } else if (($y1 <= 1.5 && $y1 >= 0) && $x1 = 0) {
            return "Успех";
        } else if (($x1 >= -1.5 && $x1 <= 0) && $y1 = 0) {
            return "Успех";
        } else if (($x1 >= -1.5 && $x1 <= 0) && ($y1 <= 1.5 && $y1 >= 0)) {
            return "Успех";
        } else if (($x1 <= 1.5 && $x1 >= 0) && ($y1 <= 0 && $y1 >= -3)) {
            return "Успех";
        } else if (($x1 <= -1.5 && $x1 <= 0) && ($y1 <= 0 && $y1 >= -3)) {
            return "Успех";
        } else {
            return "Провал";
        }
    } else if($r1==4) {
        if (($x1 <= 0.5 && $x1 >= 0) && $y1 = 0) {
            return "Успех";
        } else if (($y1 <= 0.5 && $y1 >= 0) && $x1 = 0) {
            return "Успех";
        } else if (($x1 >= -0.5 && $x1 <= 0) && $y1 = 0) {
            return "Успех";
        } else if (($x1 >= -0.5 && $x1 <= 0) && ($y1 <= 2 && $y1 >= 0)) {
            return "Успех";
        } else if (($x1 <= 0.5 && $x1 >= 0) && ($y1 <= 0 && $y1 >= -2)) {
            return "Успех";
        } else if (($x1 <= -1 && $x1 <= 0) && ($y1 <= 0 && $y1 >= -2)) {
            return "Успех";
        } else {
            return "Провал";
        }
    } else if($r1==5) {
        if (($x1 <= 0.2 && $x1 >= 0) && $y1 = 0) {
            return "Успех";
        } else if (($y1 <= 0.2 && $y1 >= 0) && $x1 = 0) {
            return "Успех";
        } else if (($x1 >= -0.2 && $x1 <= 0) && $y1 = 0) {
            return "Успех";
        } else if (($x1 >= -0.2 && $x1 <= 0) && ($y1 <= 1 && $y1 >= 0)) {
            return "Успех";
        } else if (($x1 <= 0.2 && $x1 >= 0) && ($y1 <= 0 && $y1 >= -1)) {
            return "Успех";
        } else if (($x1 <= -0.5 && $x1 <= 0) && ($y1 <= 0 && $y1 >= -1)) {
            return "Успех";
        } else {
            return "Провал";
        }
    }
}
?>