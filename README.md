# Ibalog [![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/ntmk-git/ibalog/LICENSE) 

Ibalog is a tool to save ibara-city-game logs in HTML format.
this is unofficial fan tools. please use at your own risk.

ibalogは荊街騒乱でのログをHTML形式で保存するツールです。
画像もBase64形式でHTML内に保存するため、リンク切れで画像が表示されないなどの心配はありません。

これは**非公式ファンツール**です。<br>
荊街騒乱のアップデートでうまく動かなくなる可能性もあります。<br>
ツールを使用して問題が発生した場合、予告なく本ツールを非公開にすることがあります。
マナーを守って使ってくださいね。

このツールで作成したログファイルには、荊街騒乱内で使用した発言アイコンが含まれます。<br>
<span style="color: red; ">発言アイコンの著作権は各作成者様に帰属</span>します。ログファイルの<span style="color: red; ">取り扱いには十分ご注意</span>ください。<br>
（個人使用くらいにとどめておくのが吉と思われます）

## 開発状況

本ツールはβ版です。
変な動作とかありましたら、[issues](https://github.com/ntmk-git/ibalog/issues)へ投稿してください。

### 現在できること／できないこと

- :o: とりあえずログが保存できる。
- :x: 合言葉が必要な場所のログが保存できる。
- :x: サイコロがきれいに表示される。

## ログの取り方

1. [Ibalog](https://ibalog.herokuapp.com/)にアクセスします。
1. 荊街騒乱のENOとパスワードを入力して、ログインします。
1. ログの取得方法を選択します。プレイスNOかツリーURLか選べます。
	1. ツリーURLの場合は、ツリーを**最初に表示**したときのURLをべたっと貼ってください。
1. 投稿データを取得するボタンをクリックして、取得完了までしばらく待ちます。
1. ログが取得したら、保存したいコメントブロックをクリックして選択します。選択するとコメントブロックの線の色がピンク色になります。
1. 画面右下の選択した投稿を保存するボタンをクリックしてください。ファイルがダウンロードできたら成功です。

## 動作環境

以下の環境で動作確認をしています。

- ブラウザ
	- Google Chrome
	- Firefox
	- Microsoft Edge

## 自前でデプロイしたい！

ご自由にどうぞ。Herokuとか便利です。
