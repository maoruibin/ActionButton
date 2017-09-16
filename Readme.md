## ActionButton [ ![Download](https://api.bintray.com/packages/gudong/maven/action-button/images/download.svg) ](https://bintray.com/gudong/maven/action-button/_latestVersion)   ![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)  [ ![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)
Use TextView as a ActionButton with left icon
 
## Shot
![demo](http://7xr9gx.com1.z0.glb.clouddn.com/action_button_real_9.gif)
 
## Usage

```gradle
    compile 'name.gudong:action-button:1.0.0'
 ```
```xml
    <gudong.name.button.ActionButton
        android:id="@+id/btn_follow_icon_line"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```

```java
        ActionButton button = findViewById(R.id.btn_follow_icon_line);
        button.setText("关注");
        //设置左侧 icon 以及间距
        button.setLeftIcon(R.drawable.icon_plus, ResUtils.dip2px(this, 6));
        // 设置loading drawable
        Drawable loadingDrawable = new LoadingDrawable(this, R.drawable.icon_loading_white);
        button.setLoadingDrawable(loadingDrawable);
```
for more usage info, feel free to watch [source code](./action-button/src/main/java/gudong/name/button/ActionButton.java).

### API

name | introduce
---- | ---
setLeftIcon | set left icon 
setLoadingDrawable |  set loading style by a drawable, as default use [FlowerLoading](https://github.com/maoruibin/FlowerLoading)
setOnActionClick | set click listener 

## Author

- blog&nbsp;&nbsp;&nbsp;&nbsp;[http://gudong.name](http://gudong.name)

- github [https://github.com/maoruibin](https://github.com/maoruibin)

- weibo&nbsp;&nbsp;[http://weibo.com/maoruibin](http://weibo.com/maoruibin)
 
## License

    Copyright 2017 GuDong

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 
