# NYC-retail-sales-predicting-crime-rate

## Overview

This project aims to analyze the relationship between the number of retail food stores and the crime index in the state of New York. The dataset used contains detailed information on all retail food stores licensed by the New York State Department of Agriculture and Markets, updated as of February 6, 2024. This dataset includes attributes such as street address, city, state, zip code, square footage, georeference, county, license number, type of operation, establishment type, entity name, and "Doing Business As" (DBA) name. The dataset is accessible through the NY Open Data platform and has been used to conduct various analyses.

## Dataset

- **Source**: NY Open Data platform
- **Total Records**: 24.2K
- **Columns**:
  - Street Address
  - City
  - State
  - Zip Code
  - Square Footage
  - Georeference
  - County
  - License Number
  - Type of Operation
  - Establishment Type
  - Entity Name
  - Doing Business As (DBA) Name

## Analyses

Three linear regression analyses were conducted to understand the relationship between retail store count and the crime index:

### Analysis 1: Retail Store Count and Crime Index

- **Coefficients**: [17.748552463070915]
- **Intercept**: 3461.5345893089834
- **RMSE**: 8369.159846679186
- **R²**: 0.6959647032788892

#### Conclusion
This analysis reveals a strong and direct correlation between the number of retail stores and the crime index. The R² value of 0.696 suggests substantial explanatory power, covering about 70% of the variability. However, the large RMSE points to considerable prediction errors, indicating the presence of other significant factors influencing crime rates.

### Analysis 2: Retail Store Count/Population and Crime Index

- **Coefficients**: [4.546230897594517]
- **Intercept**: 6735.515639496398
- **RMSE**: 15107.229866761385
- **R²**: 0.009327848591350829

#### Conclusion
When the retail store count is adjusted by population, the correlation with the crime index becomes significantly weaker. The very low R² of 0.009 suggests that once population is taken into account, the number of retail stores per capita provides almost no explanatory power for changes in the crime index. The high RMSE indicates a poor fit and substantial unexplained variance.

### Analysis 3: Retail Store Count/Population and Crime Index/Population

- **Coefficients**: [0.05469570299172123]
- **Intercept**: -9.899331669074101
- **RMSE**: 19.20083679697169
- **R²**: 0.4653042928222497

#### Conclusion
This analysis, which adjusts both the retail store count and the crime index by population, shows a moderate positive correlation. The R² of 0.465 indicates that nearly half of the variability in the crime index per capita can be accounted for by the number of retail stores per capita. The relatively modest RMSE compared to the second analysis suggests a better model fit, capturing more relevant dynamics between retail density and crime rates on a per capita basis.

