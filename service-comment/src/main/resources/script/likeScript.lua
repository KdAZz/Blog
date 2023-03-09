---
--- Generated by Luanalysis
--- Created by a1876.
--- DateTime: 2023/3/9 10:10
---
---
local articleId = ARGV[1]
local userId = ARGV[2]
local likeSet = ARGV[3]
local dislikeSet = ARGV[4]
local waitPushLike = ARGV[5]
local waitPushDeleteLike = ARGV[6]
local waitPushDislike = ARGV[7]
local waitPushDeleteDislike = ARGV[8]
local type = ARGV[9]

if(type == 'true') then
    if(tonumber(redis.call('SREM', dislikeSet .. articleId, userId)) == 1 and tonumber(redis.call('SREM', waitPushDislike .. articleId, userId)) ~= 1 ) then
        redis.call('SADD', waitPushDeleteDislike .. articleId, userId)
    end

    redis.call('SREM', waitPushDeleteLike .. articleId, userId)
    redis.call('SADD', likeSet .. articleId, userId)
    redis.call('SADD', waitPushLike .. articleId, userId)

    return 1
end

if(tonumber(redis.call('SREM', likeSet .. articleId, userId)) == 1 and tonumber(redis.call('SREM', waitPushLike .. articleId, userId)) ~= 1) then
    redis.call('SADD', waitPushDeleteLike .. articleId, userId)
end


redis.call('SREM', waitPushDeleteDislike .. articleId, userId)
redis.call('SADD', dislikeSet .. articleId, userId)
redis.call('SADD', waitPushDislike .. articleId, userId)

return 0