
SELECT p.id || '.' || p.descricao AS "Tipo De Conta", SUM(m.valor_movimentacao) AS "Totalizadores"
FROM 
	(SELECT * FROM primeiro_nivel WHERE id = 1) AS p 
	JOIN 
	conta c
	ON p.id = c.id_primeiro_nivel
	JOIN
	(SELECT * FROM movimentacao WHERE id_pessoa = 2 AND data_movimentacao BETWEEN '2019-06-01' AND '2019-06-30') AS m 
	ON m.id_conta = c.id_conta
GROUP BY 1

UNION ALL

SELECT c.id_primeiro_nivel || '.' || c.id_segundo_nivel || ' ' || c.descricao AS "Tipo De Conta", SUM(m.valor_movimentacao) AS "Totalizadores"
FROM 
	(SELECT * FROM conta WHERE id_primeiro_nivel = 1) AS c 
	JOIN 
	(SELECT * FROM movimentacao WHERE id_pessoa = 2 AND data_movimentacao BETWEEN '2019-06-01' AND '2019-06-30') AS m 
	ON c.id_conta = m.id_conta
GROUP BY 1

UNION ALL

SELECT p.id || '.' || p.descricao AS "Tipo De Conta", SUM(m.valor_movimentacao) AS "Totalizadores"
FROM 
	(SELECT * FROM primeiro_nivel WHERE id = 2) AS p 
	JOIN conta c ON p.id = c.id_primeiro_nivel
JOIN 
	(SELECT * FROM movimentacao WHERE id_pessoa = 2 AND data_movimentacao BETWEEN '2019-06-01' AND '2019-06-30') AS m 
	ON m.id_conta = c.id_conta
GROUP BY 1

UNION ALL

SELECT c.id_primeiro_nivel || '.' || c.id_segundo_nivel || ' ' || c.descricao AS "Tipo De Conta", SUM(m.valor_movimentacao) AS "Totalizadores"
FROM 
	(SELECT * FROM conta WHERE id_primeiro_nivel = 2) AS c 
	JOIN 
	(SELECT * FROM movimentacao WHERE id_pessoa = 2 AND data_movimentacao BETWEEN '2019-06-01' AND '2019-06-30') AS m 
	ON c.id_conta = m.id_conta
GROUP BY 1

