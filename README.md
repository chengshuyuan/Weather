# Weather
- 功能：完整Android的天气预报应用，提供了天气预报预、空气质量等信息，实现了城市管理等功能。
- 架构：整体采用MVP模式
- 框架：网络层采用OKHttp框架，并借鉴Volley框架进行了一定的封装。
- 缓存：在获取天气预报信息后，用sqlite数据库进行存储缓存
- 其他：在获取天气预报信息时需要传入城市代码，城市名称和城市代码的映射关系存储在数据库中，数据库存放在assets目录下，程序需要读取数据库文件