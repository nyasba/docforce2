<?xml version="1.0" encoding="UTF-8"?>
<Workflow xmlns="http://soap.sforce.com/2006/04/metadata">
    <alerts>
        <fullName>ApprovedMailAlert</fullName>
        <description>承認完了通知メールアラート</description>
        <protected>false</protected>
        <recipients>
            <type>owner</type>
        </recipients>
        <senderType>CurrentUser</senderType>
        <template>unfiled$public/ApprovedMail</template>
    </alerts>
    <fieldUpdates>
        <fullName>ApprovedDataTimeUpdate</fullName>
        <field>ApprovedDataTime__c</field>
        <formula>now()</formula>
        <name>承認完了日を更新</name>
        <notifyAssignee>false</notifyAssignee>
        <operation>Formula</operation>
        <protected>false</protected>
    </fieldUpdates>
</Workflow>
