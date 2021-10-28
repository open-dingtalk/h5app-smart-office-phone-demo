import react, { useEffect } from "react"
import { Button, Checkbox } from "antd"

const List = (props) => {
  let list = []
  const column = [
    {
      title: "用户名",
      dataIndex: "userName",
      key: "userName",
    },
  ]
  //   const onChange = (e) => {
  //     console.log(e.target.value, "=====")
  //     props.onChange(e)
  //   }
  const onChange = (checkedValues) => {
    console.log("checked = ", checkedValues)
    props.onChange(checkedValues)
  }
  //   if (props.list.length) {
  //     list = props.list.map((item) => ({
  //       label: item.userName,
  //       value: item.userId,
  //     }))
  //   }
  return (
    <div className="table">
      <h4 className="title">智能办公电话</h4>

      <div className="radioList">
        <Checkbox.Group onChange={onChange}>
          {props.list.map((item, index) => {
            return (
              <>
                <Checkbox value={item.userId} name={item.userName} key={index}>
                  {item.userName}
                </Checkbox>
                <br />
              </>
            )
          })}
        </Checkbox.Group>
      </div>
      {props.ids.length > 0 && (
        <Button type="primary" onClick={props.onClick}>
          开通智能电话权限
        </Button>
      )}
    </div>
  )
}

export default List
