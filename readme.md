
## docforce2

salesforceのオブジェクト構成をExcel設計書に出力するツール。

### 対応している内容

* 「オブジェクト情報」
    * オブジェクト情報
    * レコードタイプ情報
* 「カスタム項目」
    * Name項目
    * テキスト
    * テキストエリア
    * ロングテキストエリア
    * 数値
    * 通貨
    * 選択リスト
    * 複数選択リスト
    * 自動採番
    * 日付
    * 日付/時間
    * チェックボックス
    * 電話番号
    * 上記の数式項目
* 「入力規則」
* 「承認プロセス」
* 「ワークフロールール」
* 「ワークフローアクション」
    * 項目自動更新
    * メールアラート

### 使い方

1. docforce.ymlを編集
2. ```./gradlew run``` でoutput配下に出力される

```./gradlew run -Pconffile=/path/to/pdocforce.yml```のように任意のパスにある設定ファイルを指定することも可能


### docforce.yml

#### inputBaseDir

mavensmateで取得したメタデータファイルのパスを指定する。必須。

#### outputDir

Excelの出力先ディレクトリを指定する。設定しなかった場合、outputディレクトリに出力される

#### resource

カスタムオブジェクト単位で指定する。複数指定可。

| キー            | 説明       |
|:----------------|:-----------|
| object          | オブジェクトのメタデータファイルのinputBaseDirからのパス |
| approvalProcess | 承認プロセスのメタデータファイルのinputBaseDirからのパス。複数指定可 |
| workflow        | ワークフローのメタデータファイルのinputBaseDirからのパス |


#### サンプル

```
inputBaseDir : "src/test/resources/"
outputDir : "output/test"
resources :
    - resource :
          object : "object/Travel_Request__c.object"
          approvalProcesses :
            - "approvalProcess/Travel_Request__c.TravelRequestApprovalProcess.approvalProcess"
          workflow : "workflow/Travel_Request__c.workflow"
    - resource :
          object : "object/HolidayRequest__c.object"
          approvalProcesses :
          workflow : "workflow/HolidayRequest__c.workflow"
```