
## FateChoice项目说明
- FateChoice针对有选择困难症的人，提供添加选择主题，随机做一个抉择的功能。
- 暂时实现了主界面，添加主题，进行随机选择的功能。分为三个activity
- 项目原理十分简单。存储采用了 SharedPreferences，主界面根据存储的内容进行动态布局。
## 技术难点
- 每次添加主题后返回主界面需要刷新一遍，所以采用的技术方案是跳转到添加主题的activity时结束主界面的activity，返回时使用intent启动主界面，使其从新渲染。
- 按下选择按钮后，需要等待2秒后做出选择。所以使用了handler，延时2秒执行。
## 项目展示
<img src=https://www.github.com/windyear/github_save_picture/raw/master/小书匠/主界面.jpg width="200" hegiht="313" />
<img src=https://www.github.com/windyear/github_save_picture/raw/master/小书匠/添加选择.jpg width="200" hegiht="313" />
<img src=https://www.github.com/windyear/github_save_picture/raw/master/小书匠/进行选择.jpg width="200" hegiht="313" />

## 功能展望
1. 暂时没有实现删除和编辑主题的功能。
2. 可以根据使用者的个人意愿赋予不同的权值，而不是完全的随机做一个选择。

## 项目地址
[Android小项目-FateChoice 选择困难神器](https://github.com/windyear/FateChoice)

**欢迎提issue和pr**
