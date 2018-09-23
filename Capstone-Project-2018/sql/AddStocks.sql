--BULK INSERT INTO STOCK
--    FROM './companies-2018-2.csv'
--    WITH
--    (
--    FIRSTROW = 2,
--    FIELDTERMINATOR = ',',  --CSV field delimiter
--    ROWTERMINATOR = '\n',   --Use to shift the control to next row
--    ERRORFILE = 'CompanErrorRows.csv',
--    TABLOCK
--    );
INSERT INTO STOCK (CODE, NAME, TRADE_ACCOUNTSID) VALUES ('A2M', 'THE A2 MILK COMPANY LIMITED', 0),
('AAC', 'AUSTRALIAN AGRICULTURAL COMPANY LIMITED.', 0),
('ARB', 'ARB CORPORATION LIMITED.', 0),
('ASB', 'AUSTAL LIMITED', 0),
('ANZ', 'AUSTRALIA AND NEW ZEALAND BANKING GROUP LIMITED', 0),
('BAL', 'BELLAMY''S AUSTRALIA LIMITED', 0),
('BAP', 'BAPCOR LIMITED', 0),
('BBN', 'BABY BUNTING GROUP LIMITED', 0),
('BEAR', 'BETASHARES AUSTRALIAN EQUITIES BEAR HEDGE FUND', 0),
('CAB', 'CABCHARGE AUSTRALIA LIMITED', 0),
('CAR', 'CARSALES.COM LIMITED.', 0),
('CBA', 'COMMONWEALTH BANK OF AUSTRALIA.', 0),
('CIM', 'CIMIC GROUP LIMITED', 0),
('CWY', 'CLEANAWAY WASTE MANAGEMENT LIMITED', 0),
('DHG', 'DOMAIN HOLDINGS AUSTRALIA LIMITED.', 0),
('DMP', 'DOMINO''S PIZZA ENTERPRISES LIMITED', 0),
('ECX', 'ECLIPX GROUP LIMITED', 0),
('EHE', 'ESTIA HEALTH LIMITED', 0),
('FBU', 'FLETCHER BUILDING LIMITED', 0),
('FLT', 'FLIGHT CENTRE TRAVEL GROUP LIMITED', 0),
('GEM', 'G8 EDUCATION LIMITED', 0),
('GMA', 'GENWORTH MORTGAGE INSURANCE AUSTRALIA LIMITED', 0),
('GUD', 'G.U.D. HOLDINGS LIMITED', 0),
('HSN', 'HANSEN TECHNOLOGIES LIMITED', 0),
('HSO', 'HEALTHSCOPE LIMITED.', 0),
('IAG', 'INSURANCE AUSTRALIA GROUP LIMITED', 0),
('IEM', 'ISHARES MSCI EMERGING MARKETS ETF', 0),
('JBH', 'JB HI-FI LIMITED', 0),
('JHC', 'JAPARA HEALTHCARE LIMITED', 0),
('KAR', 'KAROON GAS AUSTRALIA LIMITED', 0),
('KGN', 'KOGAN.COM LTD', 0),
('LLC', 'LENDLEASE GROUP', 0),
('LNK', 'LINK ADMINISTRATION HOLDINGS LIMITED', 0),
('MFG', 'MAGELLAN FINANCIAL GROUP LIMITED', 0),
('MGR', 'MIRVAC GROUP', 0),
('NAB', 'NATIONAL AUSTRALIA BANK LIMITED', 0),
('NAN', 'NANOSONICS LIMITED', 0),
('NCM', 'NEWCREST MINING LIMITED', 0),
('OFX', 'OFX GROUP LIMITED', 0),
('OGC', 'OCEANAGOLD CORPORATION', 0),
('PGH', 'PACT GROUP HOLDINGS LTD', 0),
('PME', 'PRO MEDICUS LIMITED', 0),
('PMV', 'PREMIER INVESTMENTS LIMITED', 0),
('QAN', 'QANTAS AIRWAYS LIMITED', 0),
('QBE', 'QBE INSURANCE GROUP LIMITED', 0),
('REA', 'REA GROUP LTD', 0),
('REG', 'REGIS HEALTHCARE LIMITED', 0),
('S32', 'SOUTH32 LIMITED', 0),
('SAR', 'SARACEN MINERAL HOLDINGS LIMITED', 0),
('TAH', 'TABCORP HOLDINGS LIMITED', 0),
('TCL', 'TRANSURBAN GROUP', 0),
('TGR', 'TASSAL GROUP LIMITED', 0),
('URW', 'UNIBAIL-RODAMCO-WESTFIELD', 0),
('VCX', 'CENTRES', 0),
('VOC', 'VOCUS GROUP LIMITED', 0),
('WBC', 'WESTPAC BANKING CORPORATION', 0),
('WEB', 'WEBJET LIMITED', 0),
('XRO', 'XERO LIMITED', 0);