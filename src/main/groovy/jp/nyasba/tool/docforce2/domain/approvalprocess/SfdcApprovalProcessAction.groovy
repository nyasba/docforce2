package jp.nyasba.tool.docforce2.domain.approvalprocess

/**
 * 承認プロセスに関するアクションを定義
 */
interface SfdcApprovalProcessAction {
    def String getType()
    def String getName()
}
