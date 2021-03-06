import "./App.css"
import * as dd from "dingtalk-jsapi"
import axios from "axios"
import React from "react"
import "./App.css"
import List from "./components/List"
import "antd/dist/antd.min.css"
import { message } from "antd"

class App extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      //内网穿透工具介绍:
      // https://developers.dingtalk.com/document/resourcedownload/http-intranet-penetration?pnamespace=app
      domain: "",
      userId: "",
      corpId: "",
      userName: "",
      list: [],
      ids: [],
    }
  }
  render() {
    const corpId = window.location.href.split("=")[1]
    if (this.state.corpId === "") {
      sessionStorage.setItem("corpId", corpId)
    }
    if (this.state.userId === "") {
      this.login(corpId)
    }
    if (this.state.list === []) {
      this.getList()
    }
    return (
      <div className="App">
        <List
          list={this.state.list}
          ids={this.state.ids}
          onChange={(e) => this.addUser(e)}
          onClick={() => this.onClick()}
        />
      </div>
    )
  }

  onClick() {
    const data = {
      ids: this.state.ids.join(","),
      corpId: sessionStorage.getItem("corpId"),
    }
    axios
      .post(this.state.domain + "/biz/setCallList", JSON.stringify(data), {
        headers: { "Content-Type": "application/json" },
      })
      .then((res) => {
        if (res && res.data.success) {
          message.success("操作成功")
        } else {
          message.error("操作失败")
        }
      })
      .catch((error) => {})
  }

  addUser(e) {
    this.setState({
      ids: e,
    })
  }

  getList() {
    axios
      .get(this.state.domain + "/biz/queryOnJobList?corpId=" + sessionStorage.getItem("corpId")
      )
      .then((res) => {
        if (res && res.data.success) {
          if (res.data.data) {
            this.setState({
              list: res.data.data,
            })
          }
        } else {
          message.error("暂无数据")
        }
      })
      .catch((error) => {})
  }

  login(corpId) {
    let _this = this
    dd.runtime.permission.requestAuthCode({
      corpId: corpId, //企业 corpId
      onSuccess: function (res) {
        // 调用成功时回调
        _this.state.authCode = res.code
        axios
          .get(
            _this.state.domain +
              "/login?authCode=" +
              _this.state.authCode +
              "&corpId=" +
              corpId
          )
          .then((res) => {
            if (res && res.data.success) {
              let userId = res.data.data.userId
              let userName = res.data.data.userName
              alert("登录成功，你好" + userName)
              setTimeout(function () {
                _this.setState({
                  userId: userId,
                  userName: userName,
                })
              }, 0)
            } else {
              alert("login failed --->" + JSON.stringify(res))
            }
          })
          .catch((error) => {
            alert("httpRequest failed --->" + JSON.stringify(error))
          })
      },
      onFail: function (err) {
        // 调用失败时回调
        alert("requestAuthCode failed --->" + JSON.stringify(err))
      },
    })
  }
}

export default App
