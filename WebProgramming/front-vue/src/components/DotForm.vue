<template>
  <div id="main-content">
    <div id="up-side">
      <div id="up-left-side">
        <div id="up-left-up-side">
          <div id="up-left-left-side">
            Выберите координату X: <br> <br>
            <select v-bind:value="xValue" @input="xValue=$event.target.value" id="select-x" class="select-css">
              <option value="-5"> -5 </option>
              <option value="-4"> -4 </option>
              <option value="-3"> -3 </option>
              <option value="-2"> -2 </option>
              <option value="-1"> -1 </option>
              <option value="0"> 0 </option>
              <option value="1"> 1 </option>
              <option value="2"> 2 </option>
              <option value="3"> 3 </option>
            </select>
          </div>
          <div id="up-left-right-side">
            Выберите координату R: <br> <br>
            <select v-bind:value="rValue" @input="rValue=$event.target.value" id="select-r" class="select-css">
              <option value="-5"> -5 </option>
              <option value="-4"> -4 </option>
              <option value="-3"> -3 </option>
              <option value="-2"> -2 </option>
              <option value="-1"> -1 </option>
              <option value="0"> 0 </option>
              <option value="1"> 1 </option>
              <option value="2"> 2 </option>
              <option value="3"> 3 </option>
            </select>
          </div>
        </div>
        <div id="up-left-down-side">
          <div id="up-left-down-left-side">
            Выберите координату Y: <br> <br>
            <input v-bind:value="yValue" @input="yValue=$event.target.value" type="text" id="text-y" placeholder="-5...5">
          </div>
          <div id="ip-left-down-right-side">
            Кнопки обслуживания: <br> <br>
            <button id="sendButton" v-on:click="check"> Отправить </button>
            <button id="clearButton" v-on:click="clear"> Очистить </button>
          </div>
        </div>
      </div>
      <div class="up-right-side"  id="svg_container">
        <svg viewBox="0 0 300 300" class="svg_container" @click="emitPoint">
          <rect x="150" y="150" width="100" height="100" style="stroke-opacity: 0.6; fill: rgb(51, 153, 255);"/>
          <path style="fill: rgb(51, 153, 255);" transform="matrix(0.798786, 0, 0, 0.798786, -104.972481, 27.785759)"
                d="M 194.01 153 A 125.19 125.19 0 0 1 319.2 27.81 L 319.2 153 Z"/>
          <path d="M 24.602 -9.73 L 24.602 40.27 L -75.398 40.27 L 24.602 -9.73 Z" style="fill: rgb(51, 153, 255);"
                transform="matrix(1, -0.000002, -0.001532, -1, 125.45969, 190.269954)"/>
          <line style="stroke: rgb(0, 0, 0);" x1="25" y1="149.5" x2="275" y2="150.5"/>
          <line style="stroke: rgb(0, 0, 0);" x1="150" y1="25" x2="150" y2="275"
                transform="matrix(-1, 0, 0, -1, 300, 300)"/>
          <text
              style="fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 19.225px; font-weight: 700; white-space: pre;"
              transform="matrix(0.321932, 0, 0, 0.416125, 213.714233, 121.107925)" x="106.508" y="57.716">R
          </text>
          <line style="stroke: rgb(0, 0, 0);" x1="50" y1="148" x2="50" y2="152"/>
          <line style="stroke: rgb(0, 0, 0);" x1="100" y1="148" x2="100" y2="152"/>
          <line style="stroke: rgb(0, 0, 0);" x1="200" y1="148" x2="200" y2="152"/>
          <line style="stroke: rgb(0, 0, 0);" x1="250" y1="148" x2="250" y2="152"/>
          <line style="stroke: rgb(0, 0, 0);" x1="150" y1="48" x2="150" y2="52"
                transform="matrix(0, 1, -1, 0, 200, -100)"/>
          <line style="stroke: rgb(0, 0, 0);" x1="150" y1="248" x2="150" y2="252"
                transform="matrix(0, 1, -1, 0, 400, 100)"/>
          <line style="stroke: rgb(0, 0, 0);" x1="150" y1="98" x2="150" y2="102"
                transform="matrix(0, 1, -1, 0, 250, -50)"/>
          <line style="stroke: rgb(0, 0, 0);" x1="150" y1="198" x2="150" y2="202"
                transform="matrix(0, 1, -1, 0, 350, 50)"/>
          <text
              style="fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 19.225px; font-weight: 700; white-space: pre;"
              transform="matrix(0.321932, 0, 0, 0.416125, 120.714241, 78.107925)" x="106.508" y="57.716">R/2
          </text>
          <text
              style="fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 19.225px; font-weight: 700; white-space: pre;"
              transform="matrix(0.321932, 0, 0, 0.416125, 120.714241, 29.036404)" x="106.508" y="57.716">R
          </text>
          <text
              style="fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 19.225px; font-weight: 700; white-space: pre;"
              transform="matrix(0.321932, 0, 0, 0.416125, 120.714233, 228.107925)" x="106.508" y="57.716">-R
          </text>
          <text
              style="fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 19.225px; font-weight: 700; white-space: pre;"
              transform="matrix(0.321932, 0, 0, 0.416125, 11.71424, 121.107925)" x="106.508" y="57.716">-R
          </text>
          <text
              style="fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 19.225px; font-weight: 700; white-space: pre;"
              transform="matrix(0.321932, 0, 0, 0.416125, 120.714241, 178.107925)" x="106.508" y="57.716">-R/2
          </text>
          <text
              style="fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 19.225px; font-weight: 700; white-space: pre;"
              transform="matrix(0.321932, 0, 0, 0.416125, 58.714241, 121.107925)" x="106.508" y="57.716">-R/2
          </text>
          <text
              style="fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 19.225px; font-weight: 700; white-space: pre;"
              transform="matrix(0.321932, 0, 0, 0.416125, 158.714233, 121.107925)" x="106.508" y="57.716">-R/2
          </text>
          <g v-for="dot in dots" :key="dot.id">
            <circle r="3" :cx="this.getSvgX(dot.x)" :cy="this.getSvgY(dot.y)"
                    :fill="this.chooseColor(dot.x, dot.y)"/>
          </g>
        </svg>
      </div>
    </div>
    <hr>
    <div id="down-side" >
      <!--      <strong style="font-size: 18px">История запросов:</strong> <br> <br>-->
      <table class="result-table" id = "result_table">
        <thead>
        <tr class="result-table-header">
          <th class="result-table-th">X</th>
          <th class="result-table-th">Y</th>
          <th class="result-table-th">R</th>
          <th class="result-table-th">Текущее время</th>
          <th class="result-table-th" id="result_table_result_sell">Результат</th>
        </tr>
        </thead>
        <tbody class="result-table-body">
        <tr v-for="dot of dots"
            v-bind:dot = "dot">
          <td class="result-table-td"> {{dot.x}}</td>
          <td class="result-table-td"> {{dot.y}} </td>
          <td class="result-table-td"> {{dot.r}} </td>
          <td class="result-table-td"> {{dot.dateTime}} </td>
          <td class="result-table-td last-element-of-row"> {{dot.entry}} </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rValue: 1,
      yValue: "",
      xValue: 1,
      points: [],
      dots: [],
      new_point: {
        x: '',
        y: '',
        r: ''
      }
    }
  },

  created() {
    this.onCreate();
  },

  methods: {
    check() {
      if (this.yValue=="") {
        alert("Проверьте координат Y");
      } else if(this.yValue<=5 && this.yValue>=-5){
        this.sendPostRequest(this.xValue, this.yValue, this.rValue);
      } else {
        alert("Проверьте координат Y");
      }
    },

    onCreate() {
      let tok = localStorage.getItem("jwtToken").replace("\"", '');
      tok = tok.replace("\"", '');
      const requestOptions = {
        method: "GET",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + tok},
      };
      fetch("http://localhost:45220/web-lab4/api/entries/getEntries", requestOptions)
          .then(response => response.json())
          .then(data => {
            for (let item of data) {
              this.dots.push(item);
              this.points.push(item);
            }
          });
    },

    sendPostRequest(x,y,r) {
      let tok = localStorage.getItem("jwtToken").replace("\"", '');
      tok = tok.replace("\"", '');
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + tok},
        body: JSON.stringify({
          "x": x,
          "y": y,
          "r": r
        })
      };
      fetch("http://localhost:45220/web-lab4/api/entries/check", requestOptions)
          .then(response => response.json())
          .then(data => {
            this.dots.push(data);
            this.points.push(data);
          });
    },

    clear() {
      this.dots = []
      this.points = []
      let tok = localStorage.getItem("jwtToken").replace("\"", '');
      tok = tok.replace("\"", '');
      const requestOptions = {
        method: "DELETE",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + tok},
      };
      fetch("http://localhost:45220/web-lab4/api/entries/clearEntries", requestOptions)
          .then(response => response.json())
    },
    getSvgX(x) {
      return x / this.rValue * 100 + 150
    },
    getSvgY(y) {
      return 150 - y / this.rValue * 100
    },
    chooseColor(x, y) {
      if (x <= this.rValue && y >= - this.rValue / 2 && x >= 0 && y <= 0) return "green";
      if (x >= -this.rValue && x <= 0 && y <= 0 && y >= -this.rValue / 2) {
        if (y >= -x / 2 - this.rValue / 2) return "green";
      }
      if (y >= 0 && y <= this.rValue && x <= 0 && x >= -this.rValue) {
        if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(this.rValue, 2)) return "green";
      }
      return "red";
    },
    emitPoint(event) {
      if(localStorage.getItem("signIs") === "true") {
        let scope = this.rValue
        this.new_point.x = String(((event.pageX - document.getElementById('svg_container').offsetLeft - 150) / 99.5) * Number(scope));
        this.new_point.y = ((-(event.pageY - document.getElementById('svg_container').offsetTop - 150) / 99.5) * Number(scope));
        this.new_point.r = this.rValue
        let tok = localStorage.getItem("jwtToken").replace("\"", '');
        tok = tok.replace("\"", '');
        const requestOptions = {
          method: "POST",
          headers: { "Content-Type": "application/json", "Authorization": "Bearer " + tok},
          body: JSON.stringify({
            "x": this.new_point.x,
            "y": this.new_point.y,
            "r": this.new_point.r
          })
        };
        fetch("http://localhost:45220/web-lab4/api/entries/check", requestOptions)
            .then(response => response.json())
            .then(data => {
              this.dots.push(data);
              this.points.push(this.new_point)
            });
      } else {
        alert("Вы не авторизированный пользователь, вернитесь на страницу авторизации")
      }
    }
  }
}

</script>
<style>

#nav {
  padding: 30px;
  width: 350px;
}

#grath {
  margin-left: 100px;
  margin-bottom: 100px;
}

.svg_container {
  width: 300px;
  height: 300px;
  margin-bottom: 2em;
}
.svg_container svg {
  border: 1px solid black;
  border-radius: 1em;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
  font-size: 20px;
}

#nav a.router-link-exact-active {
  color: indigo;
}

#pre-view {
  display: flex;
  justify-content: space-between;
  width: 700px;
  margin: auto;
}

#info {
  width: 350px;
  font-size: 20px;
}

#main-content {
  margin-left: 450px;
  margin-top: 60px;
  width: 963px;
  min-height: 700px;
}

#up-side {
  width: 1300px;
  min-height: 450px;
  display: flex;
  justify-content: space-between;
}

.result-table-header th {
  padding: 10px 15px 10px 15px;
  border-right: 2px solid grey;
  width: 150px;
}

#result_table_result_sell {
  border-right: 0;
}

#up-left-side {
  width: 650px;
}

.up-right-side {
  width: 650px;
  height: 500px;
}

#down-side {
  width: 1300px;
  min-height: 400px;
}

#up-left-up-side {
  min-height: 250px;
}

#text-y {
  display: block;
  font-size: 16px;
  font-family: sans-serif;
  font-weight: 700;
  color: #444;
  line-height: 1.3;
  padding: .6em 1.4em .5em .8em;
  width: 250px;
  max-width: 100%;
  box-sizing: border-box;
  margin: 0;
  border: 1px solid #aaa;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, .04);
  border-radius: .5em;
  -moz-appearance: none;
  -webkit-appearance: none;
  text-align: center;
}

#up-left-up-side {
  display: flex;
  justify-content: space-between;
}

.result-table-td {
  text-align: center;
  border-right: 2px solid gray;
  border-top: 2px solid grey;
  padding-left: 10px;
  padding-right: 10px;
}
.last-element-of-row {
  border-right: 0;
}

.result-table {
  font-size: 22px;
  border-collapse: separate;
  border-spacing: 0 0;
}

.select-css {
  display: block;
  font-size: 16px;
  font-family: sans-serif;
  font-weight: 700;
  color: #444;
  line-height: 1.3;
  padding: .6em 1.4em .5em .8em;
  width: 250px;
  max-width: 100%;
  box-sizing: border-box;
  margin: 0;
  border: 1px solid #aaa;
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, .04);
  border-radius: .5em;
  -moz-appearance: none;
  -webkit-appearance: none;
  text-align: center;
}

#up-left-down-side {
  display: flex;
  justify-content: space-between;
}

#sendButton {
  font-size: 16px;
  font-family: sans-serif;
  font-weight: 700;
  color: #444;
  line-height: 1.3;
  padding: .6em 1.4em .5em .8em;
  width: 122px;
  max-width: 100%;
  box-sizing: border-box;
  margin: 0;
  /*border: 1px solid #aaa;*/
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, .04);
  border-radius: .5em;
  -moz-appearance: none;
  -webkit-appearance: none;
  text-align: center;
}
#clearButton {
  font-size: 16px;
  font-family: sans-serif;
  font-weight: 700;
  color: #444;
  line-height: 1.3;
  padding: .6em 1.4em .5em .8em;
  width: 122px;
  max-width: 100%;
  box-sizing: border-box;
  margin: 0;
  /*border: 1px solid #aaa;*/
  box-shadow: 0 1px 0 1px rgba(0, 0, 0, .04);
  border-radius: .5em;
  -moz-appearance: none;
  -webkit-appearance: none;
  text-align: center;
}

</style>
