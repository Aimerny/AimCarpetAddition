# Aim Carpet Addition

[![Issues](https://img.shields.io/github/issues/Aimerny/AimCarpetAddition?style=flat-square)](https://github.com/Aimerny/AimCarpetAddition/issues)
[![Pull Requests](https://img.shields.io/github/issues-pr/Aimerny/AimCarpetAddition?style=flat-square)](https://github.com/Aimerny/AimCarpetAddition/pulls)
[![Release](https://img.shields.io/github/v/release/Aimerny/AimCarpetAddition?include_prereleases&style=flat-square)](https://github.com/Aimerny/AimCarpetAddition/releases)
[![Github Release Downloads](https://img.shields.io/github/downloads/Aimerny/AimCarpetAddition/total?label=Github%20Release%20Downloads&style=flat-square)](https://github.com/Aimerny/AimCarpetAddition/releases)

[//]: # ([![Java CI with gradle]&#40;https://img.shields.io/github/workflow/status/Aimerny/AimCarpetAddition/build?label=Build&style=flat-square&#41;]&#40;https://github.com/Aimerny/AimCarpetAddition/actions/workflows/build.yml&#41;)
[//]: # ([![Publish Release]&#40;https://img.shields.io/github/workflow/status/Aimerny/AimCarpetAddition/Publish%20Release?label=Publish%20Release&style=flat-square&#41;]&#40;https://github.com/Aimerny/AimCarpetAddition/actions/workflows/publish.yml&#41;)



## 简要
AimCarpetAddition(以下简称`ACA`或`aca`)是[Carpet mod](https://github.com/gnembon/fabric-carpet) (fabric-carpet)的一个扩展.
为Carpet端提供更多的扩展特性(~~画大饼~~)

## 依赖

* `fabric-api` >= `0.76.0`
* `fabric-carpet` >= `1.4.45+v210811`
* `fabric-loader` >= `0.14.19`

## 索引

### [规则](#规则列表)

- [漏斗矿车和箱子矿车掉落物修改](#漏斗矿车和箱子矿车掉落物修改-minecartsDropSelf)

## 规则列表

### 漏斗矿车和箱子矿车掉落物修改-minecartsDropSelf

打开该选项可以使**漏斗矿车**以及**箱子矿车**在被玩家摧毁时掉落本身,而不是分解成矿车和对应的物品
> 本特性在**1.19**及以上版本已在原版支持

- 类型: `boolean`
- 默认值: `false`
- 参考选项: `true`, `false`
- 分类: `ACA`, `experimental`



