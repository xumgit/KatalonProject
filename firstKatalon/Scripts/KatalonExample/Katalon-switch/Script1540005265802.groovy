import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl('http://www.baidu.com')

WebUI.waitForPageLoad(10)

switch (searchContent) {
    case 'baidu':
        WebUI.setText(findTestObject('KatalonTestCase/Page_baidu_input/input__wd'), 'baidu')

        WebUI.click(findTestObject('KatalonTestCase/Page_baidu_click_button/input__su'))

        break
    case 'katalon':
        WebUI.setText(findTestObject('KatalonTestCase/Page_baidu_input/input__wd'), 'katalon')

        WebUI.click(findTestObject('KatalonTestCase/Page_baidu_click_button/input__su'))

        break
    default:
        WebUI.setText(findTestObject('KatalonTestCase/Page_baidu_input/input__wd'), 'cmd')

        WebUI.click(findTestObject('KatalonTestCase/Page_baidu_click_button/input__su'))

        break
}

