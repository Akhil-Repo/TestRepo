Feature: NopCom Products Validation

  @Login
  Scenario: Login to NopCommerce
    Given Launch browser
    When User Enters URL
    And User clicks on Login
    Then User should land on HomePage

  @AddProduct
  Scenario: Try to add a product
    Given User select catalog
    When Clicks on New Product
    And User Selects Basic
    And User adds Product info
    And User adds Price
    And User adds ShippingInfo
    And Select Inventory options
    Then Check the added product

  @AddFromExcel
  Scenario Outline: Try to add a products using Excel
    Given User Enters data from Excel <Sheet> & <Row>
    When User selects add new Product
    And User clicks Basic Toggle
    And Enter Product data
    And Enters price
    And Enter ShippingInfo
    And Enters Inventory options
    Then Save the product

    Examples: 
      | Sheet    | Row |
      | "Create" |   1 |
      | "Create" |   2 |
      | "Create" |   3 |
      | "Create" |   4 |
      | "Create" |   5 |

  @RetrieveProduct
  Scenario Outline: Try to Retrive Productinfo using Excel
    Given Get the data from Excel <Sheet> & <Row>
    When User searches Product
    And User see Search Result
    Then Verify Results

    Examples: 
      | Sheet      | Row |
      | "Retrieve" |   1 |

  @ModifyProduct
  Scenario Outline: Try to modify productinfo using Excel
    Given User gets data from Excel <Sheet> & <Row>
    When User selects product to modify
    And User selects Basic Toggle
    And Modify Product data
    And Modify price
    And Modify ShippingInfo
    And Add new picture
    Then Save the Changes

    Examples: 
      | Sheet    | Row |
      | "Modify" |   1 |

  @DeleteProduct
  Scenario Outline: Try to delete a product From Excel
    Given Get data from Excel <Sheet> & <Row>
    When User search Product
    And Select from Result & delete
    Then Verify Delete

    Examples: 
      | Sheet    | Row |
      | "Delete" |   3 |
