SELECT 
    CATEGORY, 
    PRICE AS MAX_PRICE, 
    PRODUCT_NAME
FROM 
    FOOD_PRODUCT
WHERE 
    -- 카테고리와 가격이 '각 카테고리별 최고가'와 정확히 일치하는 행만 뽑아냄
    (CATEGORY, PRICE) IN (
        SELECT CATEGORY, MAX(PRICE)
        FROM FOOD_PRODUCT
        WHERE CATEGORY IN ('과자', '국', '김치', '식용유')
        GROUP BY CATEGORY
    )
ORDER BY 
    MAX_PRICE DESC; -- 정렬 기준도 별칭(MAX_PRICE)으로 맞춰주면 더 깔끔합니다.